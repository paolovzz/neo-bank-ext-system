package neo.bank.ext.system.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.ext.system.domain.models.events.ControlliNonSuperati;
import neo.bank.ext.system.framework.adapter.output.kafka.integration_events.IEControlliNonSuperati;

@ApplicationScoped
public class ControlliNonSuperatiConverter implements IntegrationEventConverter<ControlliNonSuperati, IEControlliNonSuperati>, IntegrationEventConverterMarker{

    @Override
    public IEControlliNonSuperati convert(ControlliNonSuperati ev) {
        return new IEControlliNonSuperati(
            ev.idOperazione(),
            ev.ibanMittente(),
            ev.importo()
        );
    }


    @Override
    public Class<ControlliNonSuperati> supportedDomainEvent() {
        return ControlliNonSuperati.class;
    }
    
}
