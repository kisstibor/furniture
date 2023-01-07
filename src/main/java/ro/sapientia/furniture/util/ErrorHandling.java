package ro.sapientia.furniture.util;

import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.BadRequestException;

public class ErrorHandling {
    public static ResponseEntity<String> handleControllerException(Exception e) {
        if (e instanceof NotFoundException) {
            return new ResponseEntity<>(StatusMessage.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        if (e instanceof BadRequestException || e instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>(StatusMessage.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(StatusMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}