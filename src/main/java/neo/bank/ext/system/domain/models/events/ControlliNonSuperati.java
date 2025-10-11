package neo.bank.ext.system.domain.models.events;

import neo.bank.ext.system.domain.models.vo.IdOperazione;

public record ControlliNonSuperati(IdOperazione idOperazione) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliNonSuperati";
    }
}
