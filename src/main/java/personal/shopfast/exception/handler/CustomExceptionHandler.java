package personal.shopfast.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import personal.shopfast.dto.response.ErrorResponse;
import personal.shopfast.exception.DuplicateResourceException;
import personal.shopfast.exception.InvalidRequestException;
import personal.shopfast.exception.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;

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

}
