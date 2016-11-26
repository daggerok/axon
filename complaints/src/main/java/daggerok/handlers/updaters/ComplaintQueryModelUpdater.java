package daggerok.handlers.updaters;

import daggerok.domain.ComplaintQueryObject;
import daggerok.domain.ComplaintQueryObjectRepository;
import daggerok.events.ComplaintFiledEvent;
import daggerok.events.DeleteComplaintEvent;
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
     * saving applied aggregates-updaters
     */
    @EventHandler
    public void handle(ComplaintFiledEvent event) {

        log.info("handling updaters {}", event);
        // 3:
        complaintQueryObjectRepository.save(
                ComplaintQueryObject.of(event.getId(), event.getCompany(), event.getDescription()));
    }

    @EventHandler
    public void handle(DeleteComplaintEvent event) {

        log.info("handling deletion updaters {}", event);
        // 3:
        complaintQueryObjectRepository.deleteAllInBatchByCompany(event.getCompany());
    }
}
