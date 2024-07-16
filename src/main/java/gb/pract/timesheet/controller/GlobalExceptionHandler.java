package gb.pract.timesheet.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), 500);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleParseException(MethodArgumentTypeMismatchException e){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }
}
