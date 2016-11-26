package daggerok.cqrs.commands.handlers;

import daggerok.cqrs.commands.ComplaintCommand;
import daggerok.cqrs.events.ComplaintFiledEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Agregade shouldn't modify any field/state of commands!
 */
@Slf4j
@Aggregate
public class Complaint {

    /**
     * identifier is required: should be initialized on apply:
     *
     * Aggregate identifier must be non-null after applying an events.
     * Make sure the aggregate identifier is initialized at the latest when handling the creation events.
     */
    @AggregateIdentifier
    String identifier;

    /**
     * handling complaint commands
     */
    @CommandHandler
    public Complaint(ComplaintCommand command) {

        log.info("handling a commands: {}", command);
        // 2:
        apply(ComplaintFiledEvent.of(command.getId(), command.getCompany(), command.getDescription()));
    }

    // 4:
    @EventSourcingHandler
    public void on(ComplaintFiledEvent event) {
        this.identifier = event.getId();
    }
}
