package daggerok.rest;

import daggerok.events.ComplaintFiledEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@ProcessingGroup("daggerokCqrsAxonStats")
public class ClientResource {

    private final ConcurrentMap<String, AtomicLong> stats = new ConcurrentHashMap<>();

    @EventHandler
    public void handle(ComplaintFiledEvent event) {
        stats.computeIfAbsent(event.getCompany(), k -> new AtomicLong()).incrementAndGet();
    }

    @GetMapping
    public ConcurrentMap<String, AtomicLong> stats() {
        return stats;
    }
}
