package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Debito;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DebitoDTO {

    private Long id;
    private PessoaDTO pessoaDTO;
    private LocalDate dataLancamento;

    private List<ParcelaDTO> parcelasDTO;

    public static DebitoDTO convertToDTO(Debito entity) {

        DebitoDTO dto = new DebitoDTO();
        dto.setId(entity.getId());
        dto.setPessoaDTO(PessoaDTO.convertToDTO(entity.getPessoa()));
        dto.setDataLancamento(entity.getDataLancamento());
        dto.setParcelasDTO(ParcelaDTO.convertToListDTO(entity.getParcelas()));


        return dto;
    }

    public static Debito convertToEntity(DebitoDTO dto) {

        Debito entity = new Debito();

        entity.setPessoa(PessoaDTO.convertToEntity(dto.getPessoaDTO()));
        entity.setDataLancamento(dto.getDataLancamento());
        entity.setParcelas(ParcelaDTO.convertToListEntity(dto.getParcelasDTO()));


        return entity;
    }
}
