package personal.shopfast.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.shopfast.dao.repository.CustomerRepository;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController extends AbstractController<CustomerService> {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        return response(service.getAllCustomer());
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
        return response(service.getCustomerById(customerId));
    }

//    @GetMapping
//    public ResponseEntity<?> getCustomerByPhoneNumber(@RequestParam(name = "phone") String phoneNumber) {
//        return response(service.getCustomerByPhoneNumber(phoneNumber));
//    }

    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerRequest customerRequest) {
        return response(service.addNewCustomer(customerRequest));
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<?> updateCustomerById(@RequestBody CustomerRequest customerRequest) {
        return response(service.updateCustomer(customerRequest));
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String username) {
        return response(service.deleteCustomer(username));
    }
}
