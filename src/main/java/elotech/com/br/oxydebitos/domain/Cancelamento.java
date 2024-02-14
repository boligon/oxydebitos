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
@Table(name = "cancelamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Cancelamento extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator = "seqCancelamentoId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="seqCancelamentoId", sequenceName="seq_cancelamento_id", allocationSize = 1)
    private Long id;

    @Column(name = "numerocancelamento")
    @NotNull(message = "O Número de Cancelamento é obrigatório")
    private Long numeroCancelamento;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "datacancelamento")
    @NotNull(message = "A Data de Cancelamento é obrigatória")
    private LocalDate dataCancelamento;

    @OneToMany(mappedBy = "cancelamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CancelamentoItem> itensCancelamento = new ArrayList<>();

}
