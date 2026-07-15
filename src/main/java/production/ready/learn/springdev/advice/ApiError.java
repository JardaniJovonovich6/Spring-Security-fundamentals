package production.ready.learn.springdev.advice;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timestamp;
    private HttpStatus statuscode;
    private String error;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus statuscode, String error) {
        this();
        this.statuscode = statuscode;
        this.error = error;
    }
}
