package daggerok.rest;

import daggerok.commands.ComplaintCommand;
import daggerok.commands.DeleteCommand;
import daggerok.domain.ComplaintQueryObject;
import daggerok.domain.ComplaintQueryObjectRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ComplainsResource {

    private final CommandGateway commandGateway;
    private final ComplaintQueryObjectRepository complaintQueryObjectRepository;

    @GetMapping
    public List<ComplaintQueryObject> findAll() {
        return complaintQueryObjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ComplaintQueryObject findById(@PathVariable final String id) {
        return complaintQueryObjectRepository.findOne(id);
    }

    /**
     * receiving complaint aggregates
     */
    @PostMapping
    public CompletableFuture<String> fileComplaint(@RequestBody final ComplaintCommand command) {

        // 1:
        return commandGateway.send(ComplaintCommand.of(
                UUID.randomUUID().toString(), command.getCompany(), command.getDescription()));
    }

    /**
     * receiving delete aggregates
     */
    @DeleteMapping("/by/company/{company}")
    public CompletableFuture<String> deleteByCompany(@PathVariable final String company) {
        return commandGateway.send(DeleteCommand.of(UUID.randomUUID().toString(), company));
    }
}
