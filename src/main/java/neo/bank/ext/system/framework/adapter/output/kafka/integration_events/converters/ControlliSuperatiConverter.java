package neo.bank.ext.system.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.ext.system.domain.models.events.ControlliSuperati;
import neo.bank.ext.system.framework.adapter.output.kafka.integration_events.IEControlliSuperati;

@ApplicationScoped
public class ControlliSuperatiConverter implements IntegrationEventConverter<ControlliSuperati, IEControlliSuperati>, IntegrationEventConverterMarker{

    @Override
    public IEControlliSuperati convert(ControlliSuperati ev) {
        return new IEControlliSuperati(
            ev.ibanMittente(),
            ev.idOperazione(),
            ev.ibanDestinatario(),
            ev.causale(),
            ev.importo()
        );
    }


    @Override
    public Class<ControlliSuperati> supportedDomainEvent() {
        return ControlliSuperati.class;
    }
    
}
