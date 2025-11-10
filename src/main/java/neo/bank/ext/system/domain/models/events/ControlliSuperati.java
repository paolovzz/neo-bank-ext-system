package neo.bank.ext.system.domain.models.events;

public record ControlliSuperati(String idOperazione) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliSuperati";
    }
}
