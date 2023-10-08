package personal.shopfast.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
@Validated
public class CustomerController extends AbstractController<CustomerService> {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<?> getAllCustomer(@RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "3") int size) {
        return response(service.getAllCustomer(PageRequest.of(page, size)));
    }

    @GetMapping(path = "/by-id")
    public ResponseEntity<?> getCustomerById(@RequestParam(name = "id") int customerId) {
        return response(service.getCustomerById(customerId));
    }

    @GetMapping(path = "/by-username")
    public ResponseEntity<?> getCustomersByUsername(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "3") int size,
                                                    @RequestParam(name = "username") String username) {
        return response(service.getCustomersByUsername(username, PageRequest.of(page, size)));
    }

    @GetMapping(path = "/by-phone")
    public ResponseEntity<?> getCustomerByPhoneNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "3") int size,
                                                      @RequestParam(name = "phone") String phoneNumber) {
        return response(service.getCustomersByPhoneNumber(phoneNumber, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerRequest customerRequest) {
        return response(service.addNewCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateCustomerById(@RequestBody CustomerRequest customerRequest) {
        return response(service.updateCustomer(customerRequest));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteCustomerById(@RequestParam(name = "username") String username) {
        return response(service.deleteCustomer(username));
    }
}
