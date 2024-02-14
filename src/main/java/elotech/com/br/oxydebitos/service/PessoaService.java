package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.dto.PessoaDTO;
import elotech.com.br.oxydebitos.exception.RestException;
import elotech.com.br.oxydebitos.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Component
@AllArgsConstructor
public class PessoaService {
    private PessoaRepository pessoaRepository;

    public PessoaDTO findById(Long id){
        Pessoa found = this.pessoaRepository.findById(id)
                .orElseThrow(RestException::notFound);
        return PessoaDTO.convertToDTO(found);
    }

    public List<PessoaDTO> findByCnpjCpf(String cnpjCpf){
        List<Pessoa> found = this.pessoaRepository.findByCnpjCpf(cnpjCpf);

        return PessoaDTO.convertToListDTO(found);
    }

    public Page<PessoaDTO> findAll(Pageable pageable){
        return getPessoaRepository().findAll(pageable).map(PessoaDTO::convertToDTO);
    }

    @Transactional
    public PessoaDTO save(PessoaDTO dto) {
        Pessoa pessoa = PessoaDTO.convertToEntity(dto);
        Pessoa saved = getPessoaRepository().save(pessoa);
        return PessoaDTO.convertToDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        Pessoa found = this.pessoaRepository.findById(id)
                .orElseThrow(RestException::notFound);

        getPessoaRepository().delete(found);
    }

    public PessoaDTO update(Long id, PessoaDTO requestPessoaDTO){
        if (!requestPessoaDTO.getId().equals(id)) {
            throw new RuntimeException("Request não correspondente");
        }

        this.pessoaRepository.findById(id)
                .orElseThrow(RestException::conflict);

        return PessoaDTO.convertToDTO(pessoaRepository.save(PessoaDTO.convertToEntity(requestPessoaDTO)));
    }

}
