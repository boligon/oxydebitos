package elotech.com.br.oxydebitos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEBITO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Debito extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator="seqDebitoId", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="seqDebitoId", sequenceName="seq_debito_id", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa")
    private Pessoa pessoa;

    @Column(name = "datalancamento")
    @NotNull(message = "A Data de Lançamento é obrigatório")
    private LocalDate dataLancamento;

    @OneToMany(mappedBy = "debito", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DebitoParcela> parcelas = new ArrayList<>();

}
