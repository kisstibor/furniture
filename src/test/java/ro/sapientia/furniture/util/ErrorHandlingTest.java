package ro.sapientia.furniture.util;

import static org.junit.Assert.assertEquals;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.BadRequestException;

public class ErrorHandlingTest {
    @Test
    public void testHandleControllerException_NotFoundException() {
        ResponseEntity<String> responseEntity = ErrorHandling.handleControllerException(
                new NotFoundException(StatusMessage.NOT_FOUND)
        );
        assertEquals(StatusMessage.NOT_FOUND, responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleControllerException_BadRequestException() {
        ResponseEntity<String> responseEntity = ErrorHandling.handleControllerException(
                new BadRequestException(StatusMessage.BAD_REQUEST)
        );
        assertEquals(StatusMessage.BAD_REQUEST, responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleControllerException_DataIntegrityViolationException() {
        ResponseEntity<String> responseEntity = ErrorHandling.handleControllerException(
                new DataIntegrityViolationException(StatusMessage.BAD_REQUEST)
        );
        assertEquals(StatusMessage.BAD_REQUEST, responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleControllerException_Exception() {
        ResponseEntity<String> responseEntity = ErrorHandling.handleControllerException(
                new Exception()
        );
        assertEquals(StatusMessage.INTERNAL_SERVER_ERROR, responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleControllerException_null() {
        ResponseEntity<String> responseEntity = ErrorHandling.handleControllerException(null);
        assertEquals(StatusMessage.INTERNAL_SERVER_ERROR, responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
