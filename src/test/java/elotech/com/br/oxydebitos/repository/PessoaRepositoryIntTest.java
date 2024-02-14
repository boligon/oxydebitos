package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Pessoa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PessoaRepositoryIntTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void findByCnpjCpfExists() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Rafael");
        pessoa1.setCnpjCpf("39921158015");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Maria");
        pessoa2.setCnpjCpf("39921158015");

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);

        List<Pessoa> result = pessoaRepository.findByCnpjCpf("39921158015");

        assertEquals(2, result.size());

    }

    @Test
    void findByCnpjCpfNotExists() {
        String cnpjCpf = "39921158015";

        List<Pessoa> result = pessoaRepository.findByCnpjCpf(cnpjCpf);

        assertEquals(0, result.size());
    }
}