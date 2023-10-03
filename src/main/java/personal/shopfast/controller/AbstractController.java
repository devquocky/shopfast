package personal.shopfast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import personal.shopfast.exception.ResourceNotFoundException;

import java.util.Optional;

public class AbstractController<service> {

    @Autowired
    protected service service;

    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<>(response.orElseThrow(() -> new ResourceNotFoundException("Can't found resource")), HttpStatus.OK);
    }
}
