package neo.bank.ext.system.domain.models.enums;

public enum CodiceErrore {
    

    ID_NON_PUO_ESSERE_NULL("ID_NON_PUO_ESSERE_NULL");

    private String codice;

    private CodiceErrore(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return codice;
    }
    
    
}
