package personal.shopfast.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.service.CustomerService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/customer")
@Validated
public class CustomerController extends AbstractController<CustomerService> {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllCustomer(@RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "3") int size,
                                            @RequestParam(name = "sort", defaultValue = "customerId,desc") String[] sort) {
        Sort sortOption = createSortOption(sort);
        return responsePage(
                page, size,
                service.getAllCustomer(PageRequest.of(page, size, sortOption)));
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerById(@PathVariable(name = "id") int customerId) {
        return response(service.getCustomerById(customerId));
    }

    @GetMapping(path = "/username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomersByUsername(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "3") int size,
                                                    @RequestParam(name = "username")
                                                    @NotBlank(message = "Param ?username cannot be empty")
                                                    String username,
                                                    @RequestParam(name = "sort", defaultValue = "customerId,desc") String[] sort) {
        Sort sortOption = createSortOption(sort);
        return responsePage(page, size,
                service.getCustomersByUsername(username, PageRequest.of(page, size, sortOption)));
    }

    @GetMapping(path = "/phone")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerByPhoneNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "3") int size,
                                                      @RequestParam(name = "phone")
                                                      @NotBlank(message = "Param ?phone cannot be empty")
                                                      String phoneNumber,
                                                      @RequestParam(name = "sort", defaultValue = "customerId,desc")
                                                      String[] sort) {
        Sort sortOption = createSortOption(sort);
        return responsePage(page, size,
                service.getCustomersByPhoneNumber(phoneNumber, PageRequest.of(page, size, sortOption)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return response(service.addNewCustomer(customerRequest));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCustomerById(@Valid @RequestBody CustomerRequest customerRequest) {
        return response(service.updateCustomer(customerRequest));
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCustomerById(@RequestParam(name = "username")
                                                @NotBlank(message = "Param ?username cannot be empty")
                                                String username) {
        return response(service.deleteCustomer(username));
    }
}
