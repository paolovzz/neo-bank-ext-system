package neo.bank.ext.system.application.port.output;

import java.util.List;

import neo.bank.ext.system.domain.models.events.EventPayload;

public interface EventsPublisherPort {
    void publish(String aggregateName, String aggregateId, List<EventPayload> events);
}
