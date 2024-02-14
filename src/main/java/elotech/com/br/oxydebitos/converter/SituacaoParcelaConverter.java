package elotech.com.br.oxydebitos.converter;

import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class SituacaoParcelaConverter  implements AttributeConverter<SituacaoParcela, String>  {
    @Override
    public String convertToDatabaseColumn(SituacaoParcela attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SituacaoParcela convertToEntityAttribute(String dbData) {
        return SituacaoParcela.fromString(dbData);
    }
}
