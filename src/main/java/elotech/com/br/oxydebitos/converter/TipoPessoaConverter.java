package elotech.com.br.oxydebitos.converter;

import elotech.com.br.oxydebitos.enums.TipoPessoa;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String> {

    @Override
    public String convertToDatabaseColumn(TipoPessoa attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPessoa convertToEntityAttribute(String dbData) {
        return TipoPessoa.fromString(dbData);
    }
}