package neo.bank.ext.system.domain.models.events;

import neo.bank.ext.system.domain.models.vo.IdOperazione;

public record ControlliSuperati(IdOperazione idOperazione) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliSuperati";
    }
}
