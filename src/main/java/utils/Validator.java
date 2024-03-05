package utils;

import java.time.LocalDateTime;

public class Validator {
    public static LocalDateTime validarSeDataCaiNoFimDeSemana(LocalDateTime data) {
        return switch (data.getDayOfWeek()) {
            case SATURDAY -> data.plusDays(2);
            case SUNDAY -> data.plusDays(1);
            default -> data;
        };
    }
}
