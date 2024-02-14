package elotech.com.br.oxydebitos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public abstract class DefaultEntity<E extends Serializable> {
    public abstract E getId();

    public abstract void setId(E id);

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

}
