package neo.bank.ext.system.domain.models.vo;

import neo.bank.ext.system.domain.exceptions.ValidazioneException;
import neo.bank.ext.system.domain.models.enums.CodiceErrore;

public record IdOperazione(String id) {
    public IdOperazione {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                    IdOperazione.class.getSimpleName(),
                    CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice());
        }
    }
}
