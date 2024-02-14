package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {

}
