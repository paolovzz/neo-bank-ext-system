package neo.bank.ext.system.framework.adapter.input.kafka;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.ext.system.application.MockSystemUseCase;
import neo.bank.ext.system.application.port.input.dto.ApplicaControlliCmd;

@ApplicationScoped
@Slf4j
public class ContoCorrenteConsumer {

    @Inject
    private ObjectMapper mapper;
 @Inject
    private MockSystemUseCase app;

    private static final String EVENT_OWNER = "CONTO_CORRENTE";
    private static final String BONIFICO_PREDISPOSTO_EVENT_NAME = "BonificoPredisposto";
    private static final String CONTO_CORRENTE_APERTO_EVENT_NAME = "ContoCorrenteAperto";

    @Incoming("conto-corrente-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        String payload = msg.getPayload();
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            JsonNode json = convertToJsonNode(payload);
            switch (eventType) {
                case BONIFICO_PREDISPOSTO_EVENT_NAME:{
                    String ibanMittente = json.get("ibanMittente").asText();
                    String idOperazione = json.get("idOperazione").asText();
                    String ibanDestinatario = json.get("ibanDestinatario").asText();
                    String causale = json.get("causale").asText();
                    double importo =  Math.abs(json.get("importo").asDouble());
                    app.applicaControlli(new ApplicaControlliCmd(ibanMittente, idOperazione, ibanDestinatario, causale, importo));
                    break;
                }
                case CONTO_CORRENTE_APERTO_EVENT_NAME:{
                    String iban = json.get("iban").asText();
                    app.emettiBonificoExt(iban);
                    break;
                }
                default:
                    log.warn("Evento [{}] non gestito...", eventType);
                    break;
            }
        }
        return msg.ack();
    }

    private JsonNode convertToJsonNode(String payload) {
        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Errore durante la conversione json del messaggio kafka", e);
        }
    }
}
