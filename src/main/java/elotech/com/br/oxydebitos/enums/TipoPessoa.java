package elotech.com.br.oxydebitos.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum TipoPessoa {
    FISICA("F", "Física", "000.000.000-00", CpfGroup.class),
    JURIDICA("J", "Jurídica", "00.000.000/0000-00", CnpjGroup.class);

    private final String value;
    private final String descricao;
    private final String mascara;
    private final Class<?> group;

    TipoPessoa(String value, String descricao, String mascara, Class<?> group){
        this.value = value;
        this.descricao = descricao;
        this.mascara = mascara;
        this.group = group;
    }


    @JsonValue
    public String getValue() {
        return value;
    }

    public static TipoPessoa fromString(String value) {

        if (TipoPessoa.FISICA.getValue().equals(value)) {
            return FISICA;
        } else if (TipoPessoa.JURIDICA.getValue().equals(value)) {
            return JURIDICA;
        }

        return null;
    }
}
