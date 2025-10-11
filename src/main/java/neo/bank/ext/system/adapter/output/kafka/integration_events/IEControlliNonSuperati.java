package neo.bank.ext.system.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IEControlliNonSuperati implements Serializable {

    private String idOperazione;
}