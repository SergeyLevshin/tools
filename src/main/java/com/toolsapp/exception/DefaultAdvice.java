package com.toolsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException() {
        String text = "Невозможно удалить или изменить данный объект, объект ещё используется.";
        return new ResponseEntity<>(text, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleArgumentException() {
        String text = "Проверьте правильность ввода, некорректное или пустое поле.";
        return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException() {
        String text = "Нет такого элемента";
        return new ResponseEntity<>(text, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
