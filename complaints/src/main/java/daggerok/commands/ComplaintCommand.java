package daggerok.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
public class ComplaintCommand implements Serializable {
    private static final long serialVersionUID = 2499909355110271467L;

    String id, company, description;
}
