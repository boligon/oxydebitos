package elotech.com.br.oxydebitos.validator;

import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.enums.CnpjGroup;
import elotech.com.br.oxydebitos.enums.CpfGroup;
import elotech.com.br.oxydebitos.enums.TipoPessoa;
import jakarta.validation.GroupSequence;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@GroupSequence({CpfGroup.class, CnpjGroup.class, Pessoa.class })
public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

    @Override
    public List<Class<?>> getValidationGroups(Pessoa pessoa) {
        if (pessoa != null && pessoa.getTipoPessoa() != null) {
            if (TipoPessoa.FISICA.equals(pessoa.getTipoPessoa())) {
                return Arrays.asList(CpfGroup.class, Pessoa.class);
            } else if (TipoPessoa.JURIDICA.equals(pessoa.getTipoPessoa())) {
                return Arrays.asList(CnpjGroup.class, Pessoa.class);
            }
        }
        return Collections.singletonList(Pessoa.class);
    }
}
