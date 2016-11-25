package daggerok.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
public class ComplaintQueryObject {

    @Id
    String id;
    String company, description;
}
