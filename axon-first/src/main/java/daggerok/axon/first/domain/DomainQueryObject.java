package daggerok.axon.first.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainQueryObject implements Serializable {
    private static final long serialVersionUID = 9160021126413606289L;

    @Id String id;
    String company, description;
}
