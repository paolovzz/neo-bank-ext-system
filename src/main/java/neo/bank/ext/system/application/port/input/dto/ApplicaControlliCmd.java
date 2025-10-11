package neo.bank.ext.system.application.port.input.dto;

import lombok.Value;
import neo.bank.ext.system.domain.models.vo.IdOperazione;

@Value
public class ApplicaControlliCmd {
    
    private IdOperazione idOperazione;
}
