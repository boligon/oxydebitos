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
@Table(name = "PAGAMENTOITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class PagamentoItem extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator = "seqPagamentoItemId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqPagamentoItemId", sequenceName = "seq_pagamentoitem_id", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pagamento", referencedColumnName = "id")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumnOrFormula(column = @JoinColumn(name = "debitoparcela", referencedColumnName = "id"))
    private DebitoParcela debitoParcela;

    @Column(name = "valorpago")
    @NotNull(message = "O Valor Pago é obrigatório")
    private BigDecimal valorPago = BigDecimal.ZERO;

}
