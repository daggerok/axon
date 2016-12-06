package daggerok.axon.first.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DomainEvent {
    final String id, company, description;
}
