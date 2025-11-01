package neo.bank.ext.system.domain.models.events;

public record ControlliNonSuperati(
      String ibanMittente,
    String idOperazione,
    double importo
) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliNonSuperati";
    }
}
