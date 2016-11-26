package daggerok.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DeleteCommand implements Serializable {
    private static final long serialVersionUID = -8524076362587754509L;

    String id, company;
}
