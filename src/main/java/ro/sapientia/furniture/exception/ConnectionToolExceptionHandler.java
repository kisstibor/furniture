package ro.sapientia.furniture.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ConnectionToolExceptionHandler {

    @ExceptionHandler(ConnectionToolNotFoundException.class)
    public ResponseEntity<ConnectionToolErrorDetails> handleResourceNotFoundException(ConnectionToolNotFoundException exception,
                                                                        WebRequest webRequest){
    	ConnectionToolErrorDetails errorDetails = new ConnectionToolErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}