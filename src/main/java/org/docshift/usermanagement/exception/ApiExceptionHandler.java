package org.docshift.usermanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleBadReqException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        BadRequestExceptionPayload badRequestExceptionPayload = new BadRequestExceptionPayload(
                HttpStatus.BAD_REQUEST,
                new Date(),
                "Some of request fields unvalid",
                errors
        );

        log.error(ex.getMessage());

        return new ResponseEntity<>(badRequestExceptionPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFindException(UserNotFoundException ex) {
        ApiExceptionPayload apiExceptionPayload = new ApiExceptionPayload(
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage()
        );

        log.error(ex.getMessage());

        return new ResponseEntity<>(apiExceptionPayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyRegisteredException.class)
    public ResponseEntity<ApiExceptionPayload> handleRegisterException(AlreadyRegisteredException ex) {
        ApiExceptionPayload apiExceptionPayload = new ApiExceptionPayload(
                HttpStatus.BAD_REQUEST,
                new Date(),
                ex.getMessage()
        );

        log.error(ex.getMessage());

        return new ResponseEntity<>(apiExceptionPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<ApiExceptionPayload> handeNoResultException(NoResultException ex) {
        ApiExceptionPayload apiExceptionPayload = new ApiExceptionPayload(
            HttpStatus.NOT_FOUND,
            new Date(),
           "user is not is exist in db"
         );

        log.error(ex.getMessage());

        return new ResponseEntity<>(apiExceptionPayload, HttpStatus.NOT_FOUND);
    }
}
