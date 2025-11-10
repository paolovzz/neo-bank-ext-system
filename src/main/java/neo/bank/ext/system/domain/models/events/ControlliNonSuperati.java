package neo.bank.ext.system.domain.models.events;

public record ControlliNonSuperati(String idOperazione) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliNonSuperati";
    }
}
