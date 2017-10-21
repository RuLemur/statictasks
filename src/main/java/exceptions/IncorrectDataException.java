package exceptions;

/**
 * Created by RuLemur on 21.10.2017 in 21:00.
 * StaticTasks
 *
 * Ошибка тестовых данных для игр
 */
public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message) {
        super(message);
    }
}
