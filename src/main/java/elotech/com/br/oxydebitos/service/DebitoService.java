package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.converter.SituacaoParcelaConverter;
import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.domain.DebitoParcela;
import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import elotech.com.br.oxydebitos.dto.NovaParcelaDebitoDTO;
import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import elotech.com.br.oxydebitos.exception.RestException;
import elotech.com.br.oxydebitos.repository.DebitoRepository;
import elotech.com.br.oxydebitos.repository.PessoaRepository;
import elotech.com.br.oxydebitos.validator.DebitoParcelaValidator;
import elotech.com.br.oxydebitos.validator.DebitoValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Component
@AllArgsConstructor
public class DebitoService {

    private DebitoRepository debitoRepository;

    private PessoaRepository pessoaRepository;

    private DebitoValidator debitoValidator;

    private DebitoParcelaValidator debitoparcelaValidator;

    private DebitoParcelaService debitoParcelaService;

    public DebitoDTO findById(Long id){
        Debito found = this.debitoRepository.findById(id)
                .orElseThrow(RestException::notFound);
        return DebitoDTO.convertToDTO(found);
    }

    public Page<DebitoDTO> findAll(Pageable pageable){
        return getDebitoRepository().findAll(pageable).map(DebitoDTO::convertToDTO);
    }
    public Double getValorTotalDebitos() {


        List<Debito> debitos =  debitoRepository.findAll();

        double valorTotal = 0;

        for (Debito debito : debitos) {
            valorTotal += debito.getParcelas().stream().mapToDouble(i -> i.getValorParcela().doubleValue()).sum();
        }


        return valorTotal;
    }


    private void insereParcelasDebito(Debito debitoToSave, DebitoPostDTO debitoPostDTO) {
        List<DebitoParcela> debitoParcelas = debitoToSave.getParcelas();
        for (ParcelaDTO parcelaDTO : debitoPostDTO.getParcelas()) {
            DebitoParcela debitoParcela = ParcelaDTO.convertToEntity(parcelaDTO, debitoToSave);
            debitoParcelas.add(debitoParcela);
        }
    }


    @Transactional
    public DebitoDTO save(DebitoPostDTO dto) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoa()).orElseThrow(RestException::notFound);

        Debito debitoToSave = DebitoPostDTO.newEntity(dto);

        debitoToSave.setPessoa(pessoa);

        insereParcelasDebito(debitoToSave, dto);

        this.debitoValidator.validaDebito(dto);

        this.debitoparcelaValidator.validaDebitoParcela(dto);

        Debito saved = debitoRepository.save(debitoToSave);

        return DebitoDTO.convertToDTO(saved);
    }


    public DebitoDTO inserirNovaParcelaDebito(Long idDebito, NovaParcelaDebitoDTO novaParcelaDebitoDTO)  throws Exception {
        Debito debito = debitoRepository.findById(idDebito).orElseThrow(RestException::notFound);

        DebitoPostDTO debitoPostDTO = new DebitoPostDTO();
        debitoPostDTO.setId(idDebito);
        debitoPostDTO.setPessoa(debito.getPessoa().getId());
        debitoPostDTO.setDataLancamento(debito.getDataLancamento());

        List<ParcelaDTO> listParcela = new ArrayList<>();

        listParcela.add(ParcelaDTO.newEntity(novaParcelaDebitoDTO.getNumeroParcela(),
                                             novaParcelaDebitoDTO.getDataVencimento(),
                                             novaParcelaDebitoDTO.getValorParcela()
        ));

        debitoPostDTO.setParcelas(listParcela);

        insereParcelasDebito(debito, debitoPostDTO);

        this.debitoparcelaValidator.validaDebitoParcela(debitoPostDTO);

        return DebitoDTO.convertToDTO(this.debitoRepository.save(debito));
    }

    @Transactional
    public void delete(Long id) {
        Debito found;
        found = this.debitoRepository.findById(id)
                .orElseThrow(RestException::notFound);

        getDebitoRepository().delete(found);
    }

    public DebitoDTO update(Long id, DebitoDTO requestDebitoDTO){
        if (!requestDebitoDTO.getId().equals(id)) {
            throw new RuntimeException("Request não correspondente");
        }

        this.debitoRepository.findById(id)
                .orElseThrow(RestException::conflict);

        return DebitoDTO.convertToDTO(debitoRepository.save(DebitoDTO.convertToEntity(requestDebitoDTO)));
    }

    public Page<DebitoDTO> findAllByPessoa(Pageable pageable, Long id){
        List<DebitoDTO> pessoaDebitosDTO = this.debitoRepository.findAllByPessoaId(id)
                .stream().map(DebitoDTO::convertToDTO).toList();

        List<DebitoDTO> listDebitosDTO = pessoaDebitosDTO.stream()
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, pessoaDebitosDTO.size());
    }

    public Page<DebitoDTO> findAllByCnpjCpf(Pageable pageable, String cnpjCpf){
        List<DebitoDTO> pessoaDebitosDTO = this.debitoRepository.findAllByPessoaCnpjCpf(cnpjCpf)
                .stream().map(DebitoDTO::convertToDTO).toList();

        List<DebitoDTO> listDebitosDTO = pessoaDebitosDTO.stream()
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, pessoaDebitosDTO.size());
    }

    public Page<DebitoDTO> findAllByPeriodoDataLancamento(Pageable pageable, LocalDate dataInicial, LocalDate dataFinal){

        dataFinal = Objects.isNull(dataFinal) ? dataInicial : dataFinal;

        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByDataLancamentoBetween(dataInicial, dataFinal)
                .stream().map(DebitoDTO::convertToDTO).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream()
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }

    public Page<DebitoDTO> findAllByPeriodoDataLancamentoAndPessoa(Pageable pageable, String nome, LocalDate dataInicial, LocalDate dataFinal){

        dataFinal = Objects.isNull(dataFinal) ? dataInicial : dataFinal;

        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByPessoaNomeAndDataLancamentoBetween(nome, dataInicial, dataFinal)
                .stream().map(DebitoDTO::convertToDTO).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream()
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }



    public Page<DebitoDTO> findAllBySituacaoParcela(Pageable pageable, String situacaoParcela){
        SituacaoParcelaConverter converter = new SituacaoParcelaConverter();
        SituacaoParcela situacaoParcelaEnum = converter.convertToEntityAttribute(situacaoParcela);
        List<DebitoDTO> situacaoDebitosDTO = this.debitoRepository.findByParcelas_SituacaoParcela(situacaoParcelaEnum)
                .stream().map(DebitoDTO::convertToDTO).toList();

        List<DebitoDTO> listDebitosDTO = situacaoDebitosDTO.stream()
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, situacaoDebitosDTO.size());
    }

}