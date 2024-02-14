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
@Table(name = "PAGAMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Pagamento extends DefaultEntity<Long> {

    @Id
    @GeneratedValue(generator = "seqPagamentoId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqPagamentoId", sequenceName = "seq_pagamento_id", allocationSize = 1)
    private Long id;

    @Column(name = "numeropagamento")
    @NotNull(message = "O Número do Pagamento é obrigatório")
    private Long numeroPagamento;

    @Column(name = "datapagamento")
    @NotNull(message = "A Data de Pagamento é obrigatória")
    private LocalDate dataPagamento;

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagamentoItem> itensPagamento = new ArrayList<>();

}
