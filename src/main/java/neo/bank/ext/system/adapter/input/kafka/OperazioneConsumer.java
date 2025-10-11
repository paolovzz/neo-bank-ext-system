package neo.bank.ext.system.adapter.input.kafka;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.ext.system.application.MockSystemUseCase;
import neo.bank.ext.system.application.port.input.dto.ApplicaControlliCmd;
import neo.bank.ext.system.domain.models.vo.IdOperazione;

@ApplicationScoped
@Slf4j
public class OperazioneConsumer {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private MockSystemUseCase app;

    private static final String EVENT_OWNER = "OPERAZIONE";
    private static final String OPERAZIONE_AVVIATA_EVENT_NAME = "OperazioneAvviata";

    @Incoming("operazione-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            switch (eventType) {
                case OPERAZIONE_AVVIATA_EVENT_NAME:{
                    String idOperazione =  (String) metadata.getKey();
                    app.applicaControlli(new ApplicaControlliCmd(new IdOperazione(idOperazione)));
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
