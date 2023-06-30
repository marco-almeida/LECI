package PooRecurso2022_23;

public enum SensitivityLevel {
    PERSONAL // NAO PODE SER PARTILHADO
    , RESTRICTED // PODE SER PARTILHADO COM UTILIZADORES REGISTADOS
    , LOW; // PODE SER TORNADO TOTALMENTE PUBLICO E ACEDIDO POR QUALQUER UTILIZADOR

    @Override
    public String toString() {
        return switch (this) {
            case PERSONAL -> "Personal";
            case RESTRICTED -> "Restricted";
            case LOW -> "Low";
        };
    }
}
