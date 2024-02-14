package elotech.com.br.oxydebitos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.math.BigDecimal;

@Entity
@Table(name = "CANCELAMENTOITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class CancelamentoItem extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator = "seqCancelamentoItemId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="seqCancelamentoItemId", sequenceName="seq_cancelamentoitem_id", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cancelamento", referencedColumnName = "id")
    private Cancelamento cancelamento;

    @ManyToOne
    @JoinColumnOrFormula(column = @JoinColumn(name = "debitoparcela", referencedColumnName = "id"))
    private DebitoParcela debitoParcela;

    @Column(name = "valorcancelado")
    @NotNull(message = "O Valor do Cancelamento é obrigatório")
    private BigDecimal valorCancelado = BigDecimal.ZERO;

}
