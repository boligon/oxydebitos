package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByCnpjCpf(String cnpjCpf);

}
