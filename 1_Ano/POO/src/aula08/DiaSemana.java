package aula08;

public enum DiaSemana {
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaSemana getEnum(int dia){
        return DiaSemana.values()[dia];
    }
}
