package ro.sapientia.furniture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConnectionToolNotFoundException extends RuntimeException{
    private long id;

    public ConnectionToolNotFoundException(long id) {
        super(String.format("Connection tool not found with id : '%s'", id)); // Post not found with id : 1
        this.id = id;
    }


}