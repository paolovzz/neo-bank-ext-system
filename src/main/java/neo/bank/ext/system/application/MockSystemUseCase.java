package neo.bank.ext.system.application;

import java.util.List;
import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.ext.system.application.port.input.dto.ApplicaControlliCmd;
import neo.bank.ext.system.application.port.output.EventsPublisherPort;
import neo.bank.ext.system.domain.models.events.ControlliNonSuperati;
import neo.bank.ext.system.domain.models.events.ControlliSuperati;

@ApplicationScoped
@Slf4j
public class MockSystemUseCase {

    @Inject
    private EventsPublisherPort publisherPort;

    // private static final List<String> LISTA_MOCK_IBAN_MITTENTI = List.of(
    //             "IT24S0300203280736176532974",
    //             "IT58L0300203280567839428523",
    //             "IT83H0300203280417693495318",
    //             "IT43Y0300203280538229824321",
    //             "IT11P0300203280444338928292");

    /*
     * Metodo che simulare il sistema esterno a quello bancario:
     * il suo scopo è simulare casistiche in cui alcune
     * operazioni vanno a buon fine ed altre no
     */
    public void applicaControlli(ApplicaControlliCmd cmd) {
        log.info("Comando [applicaControlli] in esecuzione...");
        Random random = new Random();
        double numeroCasuale = random.nextDouble(); // Numero tra 0.0 e 1.0

        log.info("Numero Casuale: {}", numeroCasuale);
        if (numeroCasuale < 0.10) {
            publisherPort.publish("SISTEMA_ESTERNO", null,
                    List.of(new ControlliNonSuperati(cmd.getIdOperazione())));
        } else {
            publisherPort.publish("SISTEMA_ESTERNO", null, List.of(new ControlliSuperati(cmd.getIdOperazione())));
        }
        log.info("Comando [applicaControlli] terminato...");
    }

}