package elotech.com.br.oxydebitos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import elotech.com.br.oxydebitos.converter.SituacaoParcelaConverter;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DEBITOPARCELA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class DebitoParcela extends DefaultEntity<Long> {
    @Id
    @GeneratedValue(generator = "seqDebitoParcelaId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="seqDebitoParcelaId", sequenceName="seq_debitoparcela_id", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "debito", referencedColumnName = "id")
    private Debito debito;

    @Column(name = "numeroparcela")
    @NotNull(message = "O Número da Parcela é obrigatória")
    private Long numeroParcela;

    @Column(name = "datavencimento")
    @NotNull(message = "A Data de Vencimento é obrigatória")
    private LocalDate dataVencimento;

    @Column(name = "situacaoparcela", length = 1)
    @NotNull(message = "Situação da Parcela é obrigatória")
    @Convert(converter = SituacaoParcelaConverter.class)
    private SituacaoParcela situacaoParcela;

    @Column(name = "valorparcela")
    @NotNull(message = "O Valor da Parcela é obrigatório")
    private BigDecimal valorParcela = BigDecimal.ZERO;

}
