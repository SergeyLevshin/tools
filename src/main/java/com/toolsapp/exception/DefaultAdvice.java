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
    public ResponseEntity<String> handleSQLException(SQLException e) {
        String text = "Невозможно удалить или изменить данный объект, объект ещё используется."
                +"<br/> Или такой объект уже существует.<br/>"
                + "<br/>" + e.getClass().getCanonicalName()
                + "<br/>" + e.getMessage();;
        return new ResponseEntity<>(text, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleArgumentException(IllegalStateException e) {
        String text = "Проверьте правильность ввода, некорректное или пустое поле. <br/>"
                + "<br/>" + e.getClass().getCanonicalName()
                + "<br/>" + e.getMessage();
        return new ResponseEntity<>(text, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        String text = "Нет такого элемента<br/>"
                + "<br/>" + e.getClass().getCanonicalName()
                + "<br/>" + e.getMessage();;
        return new ResponseEntity<>(text, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
