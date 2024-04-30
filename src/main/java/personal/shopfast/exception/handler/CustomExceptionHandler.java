package personal.shopfast.exception.handler;

import lombok.NonNull;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import personal.shopfast.dto.response.ErrorResponse;
import personal.shopfast.exception.DuplicateResourceException;
import personal.shopfast.exception.InvalidRequestException;
import personal.shopfast.exception.ResourceNotFoundException;
import personal.shopfast.exception.TokenRefreshException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(
                "000", e.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException e) {
        return new ResponseEntity<>(new ErrorResponse(
                "000", e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse(
                "000", e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
        return new ResponseEntity<>(new ErrorResponse(
                "000", e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PropertyReferenceException.class})
    public ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException e) {
        return new ResponseEntity<>(new ErrorResponse(
                "000", e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        // Extract error message from exception
        List<ErrorResponse> errors = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            errors.add(new ErrorResponse(objectError.getCode(), objectError.getDefaultMessage()));
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(Integer.toString(HttpStatus.FORBIDDEN.value()), ex.getMessage()),
                HttpStatus.FORBIDDEN);
    }
}
