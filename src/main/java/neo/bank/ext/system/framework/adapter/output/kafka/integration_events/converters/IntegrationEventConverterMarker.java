package neo.bank.ext.system.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.ext.system.domain.models.events.EventPayload;

public interface IntegrationEventConverterMarker {
    Class<? extends EventPayload> supportedDomainEvent();
}
