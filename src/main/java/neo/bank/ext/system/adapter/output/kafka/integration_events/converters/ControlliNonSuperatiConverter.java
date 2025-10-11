package neo.bank.ext.system.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.ext.system.adapter.output.kafka.integration_events.IEControlliNonSuperati;
import neo.bank.ext.system.domain.models.events.ControlliNonSuperati;
import neo.bank.ext.system.domain.models.events.ControlliSuperati;

@ApplicationScoped
public class ControlliNonSuperatiConverter implements IntegrationEventConverter<ControlliNonSuperati, IEControlliNonSuperati>, IntegrationEventConverterMarker{

    @Override
    public IEControlliNonSuperati convert(ControlliNonSuperati ev) {
        return new IEControlliNonSuperati(ev.idOperazione().id());
    }


    @Override
    public Class<ControlliSuperati> supportedDomainEvent() {
        return ControlliSuperati.class;
    }
    
}
