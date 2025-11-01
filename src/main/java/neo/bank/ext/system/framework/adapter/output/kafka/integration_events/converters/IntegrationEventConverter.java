package neo.bank.ext.system.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.ext.system.domain.models.events.EventPayload;

public interface IntegrationEventConverter<DE extends EventPayload, IE> {
    IE convert(DE domainEvent);
}