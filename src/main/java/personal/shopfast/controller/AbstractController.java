package personal.shopfast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import personal.shopfast.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractController<service> {

    @Autowired
    protected service service;

    /**
     * Casting the return type of ResponseEntity based on the output type of service class.
     * And handle Null Exceptions by using Optional.orElseThrow()
     *
     * @param serviceOutput
     * @param <T>
     * @return ResponseEntity
     */
    protected <T> ResponseEntity<T> response(Optional<T> serviceOutput) {
        return new ResponseEntity<>(serviceOutput.orElseThrow(() -> new ResourceNotFoundException("Can't found resource")), HttpStatus.OK);
    }

    /**
     * Convert String direction (asc,desc) into object Sort.Direction
     *
     * @param direction
     * @return Direction
     */
    private Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Direction.ASC;
        } else if (direction.equals("desc")) {
            return Direction.DESC;
        }
        return Direction.ASC;
    }

    /**
     * Create Sort Oject for PageRequest based on the input is array String sort.
     *
     * @param sort
     * @return Sort Object
     */
    protected Sort createSortOption(String[] sort) {
        // sortOrder="field, direction"
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return Sort.by(orders);
    }
}
