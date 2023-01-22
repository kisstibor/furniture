package ro.sapientia.furniture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FurniturePanelNotFoundException extends RuntimeException {

    public FurniturePanelNotFoundException(String msg) {
        super(msg);
    }
}