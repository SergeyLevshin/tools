package com.toolsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        String text = "Невозможно удалить или изменить данный объект, объект ещё используется.";
        return new ResponseEntity<>(text, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleArgumentException(IllegalArgumentException e) {
        String text = "Проверьте правильность ввода, некорректное или пустое поле.";
        return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);
    }
}
