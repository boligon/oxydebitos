package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.DebitoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitoParcelaRepository  extends JpaRepository<DebitoParcela, Long>  {

    @Query(" select p from Debito d "
           + " join DebitoParcela p on p.debito.id = d.id "
           + " where d.id = :id "
           + "   and p.numeroParcela = :numeroParcela ")
    DebitoParcela findParcelaByIdAndNumeroParcela(@Param("id") Long id, @Param("numeroParcela") Long numeroParcela);

}
