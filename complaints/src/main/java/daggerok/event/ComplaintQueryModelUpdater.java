package daggerok.event;

import daggerok.domain.ComplaintQueryObject;
import daggerok.domain.ComplaintQueryObjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ComplaintQueryModelUpdater {

    final ComplaintQueryObjectRepository complaintQueryObjectRepository;

    /**
     * saving applied command-event
     */
    @EventHandler
    public void handle(ComplaintFiledEvent event) {

        log.info("handling event {}", event);
        // 3:
        complaintQueryObjectRepository.save(
                ComplaintQueryObject.of(event.getId(), event.getCompany(), event.getDescription()));
    }
}
