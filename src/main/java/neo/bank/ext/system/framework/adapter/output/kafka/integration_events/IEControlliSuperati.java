package neo.bank.ext.system.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IEControlliSuperati implements Serializable {

    String ibanMittente;
    String idOperazione;
    String ibanDestinatario;
    String causale;
    double importo;
}