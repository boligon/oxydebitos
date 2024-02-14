package elotech.com.br.oxydebitos.domain;

import elotech.com.br.oxydebitos.converter.TipoPessoaConverter;
import elotech.com.br.oxydebitos.enums.CnpjGroup;
import elotech.com.br.oxydebitos.enums.CpfGroup;
import elotech.com.br.oxydebitos.enums.TipoPessoa;
import elotech.com.br.oxydebitos.validator.PessoaGroupSequenceProvider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@Entity
@Table(name = "PESSOA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@GroupSequenceProvider(PessoaGroupSequenceProvider.class)
public class Pessoa extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator="seqPessoa", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="seqPessoa", sequenceName="seq_pessoa_id",  allocationSize = 1)
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "O Nome da Pessoa é obrigatório")
    private String nome;

    @Column(name = "cnpjcpf")
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @NotNull(message = "O Documento da Pessoa é obrigatório")
    private String cnpjCpf;

    @Column(name = "tipopessoa", length = 1)
    @NotNull(message = "TipoPessoa é obrigatório")
    @Convert(converter = TipoPessoaConverter.class)
    private TipoPessoa tipoPessoa;

}
