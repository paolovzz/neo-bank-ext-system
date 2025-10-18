package neo.bank.ext.system.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.ext.system.adapter.output.kafka.integration_events.IEControlliNonSuperati;
import neo.bank.ext.system.domain.models.events.ControlliNonSuperati;

@ApplicationScoped
public class ControlliNonSuperatiConverter implements IntegrationEventConverter<ControlliNonSuperati, IEControlliNonSuperati>, IntegrationEventConverterMarker{

    @Override
    public IEControlliNonSuperati convert(ControlliNonSuperati ev) {
        return new IEControlliNonSuperati(ev.idOperazione().id());
    }


    @Override
    public Class<ControlliNonSuperati> supportedDomainEvent() {
        return ControlliNonSuperati.class;
    }
    
}
