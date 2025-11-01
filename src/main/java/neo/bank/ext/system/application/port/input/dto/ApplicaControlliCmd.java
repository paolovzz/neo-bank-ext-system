package neo.bank.ext.system.application.port.input.dto;

import lombok.Value;

@Value
public class ApplicaControlliCmd {
    
    String ibanMittente;
      String idOperazione;
    String ibanDestinatario;
    String causale;
    double importo;
}
