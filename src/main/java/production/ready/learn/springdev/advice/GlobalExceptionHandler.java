package production.ready.learn.springdev.advice;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import production.ready.learn.springdev.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apierror = new ApiError(HttpStatus.NOT_FOUND , exception.getMessage());
        return new ResponseEntity<>(apierror , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception){
        ApiError apierror = new ApiError(HttpStatus.UNAUTHORIZED , exception.getMessage());
        return new ResponseEntity<>(apierror , HttpStatus.UNAUTHORIZED );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException exception) {
        ApiError apierror = new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(apierror, HttpStatus.UNAUTHORIZED);
    }




}
