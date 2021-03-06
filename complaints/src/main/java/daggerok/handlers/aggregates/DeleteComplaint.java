package daggerok.handlers.aggregates;

import daggerok.commands.DeleteCommand;
import daggerok.events.DeleteComplaintEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class DeleteComplaint {

    @AggregateIdentifier
    String identifier;

    @CommandHandler
    public DeleteComplaint(DeleteCommand command) {

        log.info("handling a aggregates: {}", command);
        apply(DeleteComplaintEvent.of(command.getId(), command.getCompany()));
    }

    @EventSourcingHandler
    public void on(DeleteComplaintEvent event) {
        this.identifier = event.getId();
    }
}
