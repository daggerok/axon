package daggerok.handlers.aggregates;

import daggerok.commands.ComplaintCommand;
import daggerok.events.ComplaintFiledEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Agregade shouldn't modify any field/state of aggregates!
 */
@Slf4j
@Aggregate
public class Complaint {

    /**
     * identifier is required: should be initialized on apply:
     * <p>
     * Aggregate identifier must be non-null after applying an updaters.
     * Make sure the aggregate identifier is initialized at the latest when handling the creation updaters.
     */
    @AggregateIdentifier
    String identifier;

    /**
     * handling complaint aggregates
     */
    @CommandHandler
    public Complaint(ComplaintCommand command) {

        log.info("handling a aggregates: {}", command);
        // 2:
        apply(ComplaintFiledEvent.of(command.getId(), command.getCompany(), command.getDescription()));
    }

    // 4:
    @EventSourcingHandler
    public void on(ComplaintFiledEvent event) {
        this.identifier = event.getId();
    }
}
