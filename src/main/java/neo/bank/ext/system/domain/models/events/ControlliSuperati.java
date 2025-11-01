package neo.bank.ext.system.domain.models.events;

public record ControlliSuperati(    
    String ibanMittente,
    String idOperazione,
    String ibanDestinatario,
    String causale,
    double importo
) implements EventPayload {

    @Override
    public String eventType() {
        return "ControlliSuperati";
    }
}
