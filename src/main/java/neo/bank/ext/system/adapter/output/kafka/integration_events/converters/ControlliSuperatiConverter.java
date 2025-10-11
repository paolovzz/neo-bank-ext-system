package neo.bank.ext.system.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.ext.system.adapter.output.kafka.integration_events.IEControlliSuperati;
import neo.bank.ext.system.domain.models.events.ControlliSuperati;

@ApplicationScoped
public class ControlliSuperatiConverter implements IntegrationEventConverter<ControlliSuperati, IEControlliSuperati>, IntegrationEventConverterMarker{

    @Override
    public IEControlliSuperati convert(ControlliSuperati ev) {
        return new IEControlliSuperati(ev.idOperazione().id());
    }


    @Override
    public Class<ControlliSuperati> supportedDomainEvent() {
        return ControlliSuperati.class;
    }
    
}
