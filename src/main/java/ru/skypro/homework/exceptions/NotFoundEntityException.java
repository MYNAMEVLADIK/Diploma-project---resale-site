package ru.skypro.homework.exceptions;

/**
 * Класс - обработчик исключений
 */
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}
