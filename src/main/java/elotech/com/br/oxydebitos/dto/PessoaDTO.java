package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.enums.TipoPessoa;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cnpjCpf;
    private TipoPessoa tipoPessoa;

    public static PessoaDTO convertToDTO(Pessoa entity) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpjCpf(entity.getCnpjCpf());
        dto.setTipoPessoa(entity.getTipoPessoa());

        return dto;
    }

    public static Pessoa convertToEntity(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCnpjCpf(dto.getCnpjCpf());
        entity.setTipoPessoa(dto.getTipoPessoa());

        return entity;
    }

    public static List<PessoaDTO> convertToListDTO(List<Pessoa> pessoas) {
        List<PessoaDTO> pessoasDTO = new ArrayList<>();

        for (Pessoa entity: pessoas) {
            PessoaDTO dto = new PessoaDTO();
            dto.setId(entity.getId());
            dto.setNome(entity.getNome());
            dto.setCnpjCpf(entity.getCnpjCpf());
            dto.setTipoPessoa(entity.getTipoPessoa());

            pessoasDTO.add(dto);
        }
        return pessoasDTO;
    }

}
