package ro.sapientia.furniture.exception;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConnectionToolErrorDetails {

	 private Date timestamp;
	    private String message;
	    private String details;

	    public ConnectionToolErrorDetails(Date timestamp, String message, String details) {
	        this.timestamp = timestamp;
	        this.message = message;
	        this.details = details;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getDetails() {
	        return details;
	    }
}
