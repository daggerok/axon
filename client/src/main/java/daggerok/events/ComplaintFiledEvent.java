package daggerok.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ComplaintFiledEvent {

    String id, company, description;
}
