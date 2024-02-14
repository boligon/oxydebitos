package elotech.com.br.oxydebitos.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SituacaoParcela {
    ABERTO("A", "Aberto"),
    PAGO("P", "Pago"),
    CANCELADO("C", "Cancelado");

    private final String value;
    private final String descricao;

    SituacaoParcela(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static SituacaoParcela fromString(String value) {

        if (SituacaoParcela.ABERTO.getValue().equals(value)) {
            return ABERTO;
        } else if (SituacaoParcela.PAGO.getValue().equals(value)) {
            return PAGO;
        } else if (SituacaoParcela.CANCELADO.getValue().equals(value)) {
            return CANCELADO;
        }

        return null;
    }

}
