package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Long> {

    List<Debito> findAllByPessoaId(Long id);

    List<Debito> findAllByPessoaCnpjCpf(String cnpjCpf);

    List<Debito> findAllByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    List<Debito> findAllByPessoaNomeAndDataLancamentoBetween(String nome, LocalDate dataInicial, LocalDate dataFinal);

    List<Debito> findByParcelas_SituacaoParcela(SituacaoParcela situacaoParcela);
}
