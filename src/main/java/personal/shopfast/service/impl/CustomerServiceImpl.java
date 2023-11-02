package personal.shopfast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import personal.shopfast.dao.entity.Customer;
import personal.shopfast.dao.repository.CustomerRepository;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.dto.response.CustomerResponse;
import personal.shopfast.exception.DuplicateResourceException;
import personal.shopfast.exception.InvalidRequestException;
import personal.shopfast.exception.ResourceNotFoundException;
import personal.shopfast.service.AbstractService;
import personal.shopfast.service.CustomerService;
import personal.shopfast.util.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.*;
import static personal.shopfast.dao.specification.CustomerSpecification.*;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Optional<List<CustomerResponse>> getAllCustomer(PageRequest pageRequest) {
        return Optional.of(customerRepository.findAll(where(isActive()), pageRequest)
                .toList().stream().map(customer ->
                        ObjectMapper.map(customer, CustomerResponse.class)
                ).collect(Collectors.toList())
        );
    }

    @Override
    public Optional<CustomerResponse> getCustomerById(int customerId) {
        return customerRepository.findOne(where(hasId(customerId)).and(isActive()))
                .map(customer -> {
                    CustomerResponse customerResponse = ObjectMapper.map(customer, CustomerResponse.class);
                    return Optional.of(customerResponse);
                })
                .orElseThrow(() -> new ResourceNotFoundException("This customer does not exist"));
    }

    @Override
    public Optional<List<CustomerResponse>> getCustomersByPhoneNumber(String phoneNumber, PageRequest pageRequest) {
        List<Customer> foundCustomers = customerRepository.
                findAll(where(isActive()).and(likePhoneNumber(phoneNumber)), pageRequest).toList();

        if (foundCustomers.isEmpty()) {
            throw new ResourceNotFoundException("This customer does not exist");
        }

        List<CustomerResponse> customersResponse = foundCustomers.stream().map(customer ->
                ObjectMapper.map(customer, CustomerResponse.class)
        ).collect(Collectors.toList());

        return Optional.of(customersResponse);
    }

    @Override
    public Optional<List<CustomerResponse>> getCustomersByUsername(String username, PageRequest pageRequest) {
        List<Customer> foundCustomers = customerRepository.
                findAll(where(isActive()).and(likeUsername(username)), pageRequest).toList();

        if (foundCustomers.isEmpty()) {
            throw new ResourceNotFoundException("This customer does not exist");
        }

        List<CustomerResponse> customersResponse = foundCustomers.stream().map(customer ->
                ObjectMapper.map(customer, CustomerResponse.class)
        ).collect(Collectors.toList());

        return Optional.of(customersResponse);
    }

    @Override
    public Optional<CustomerResponse> addNewCustomer(CustomerRequest customerRequest) {
        // Validate customer request
        validateCustomerRequest(customerRequest);

        List<Customer> foundCustomers = customerRepository.findAll(
                where(likeUsername(customerRequest.getUsername()))
                        .and(likePhoneNumber(customerRequest.getPhoneNumber())));

        // Validate duplicate phone number
        if (foundCustomers.stream().anyMatch(customer ->
                customer.getUsername().equals(customerRequest.getUsername()))) {
            throw new DuplicateResourceException("This username is existed");
        }

        // Validate duplicate phone number
        if (foundCustomers.stream().anyMatch(customer ->
                customer.getPhoneNumber().equals(customerRequest.getPhoneNumber()))) {
            throw new DuplicateResourceException("This phone number is used for another user");
        }

        Customer newCustomer = ObjectMapper.map(customerRequest, Customer.class);
        customerRepository.save(newCustomer);

        return Optional.of(ObjectMapper.map(newCustomer, CustomerResponse.class));
    }

    @Override
    public Optional<CustomerResponse> updateCustomer(CustomerRequest customerRequest) {
        // Validate customer request
        validateCustomerRequest(customerRequest);

        Customer foundCustomer = customerRepository.findOne(where(isActive())
                        .and(hasUsername(customerRequest.getUsername())))
                .orElseThrow(() ->
                        new ResourceNotFoundException("This customer does not exist")
                );

        Customer newModifiedCustomer = ObjectMapper.map(customerRequest, Customer.class);
        newModifiedCustomer.setCustomerId(foundCustomer.getCustomerId());
        newModifiedCustomer.setModifiedTime(LocalDateTime.now());
        customerRepository.save(newModifiedCustomer);

        return Optional.of(ObjectMapper.map(foundCustomer, CustomerResponse.class));
    }

    @Override
    public Optional<CustomerResponse> deleteCustomer(String username) {
        Customer foundCustomer = customerRepository.findOne(where(isActive()).and(hasUsername(username)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("This customer does not exist")
                );

        foundCustomer.setDeleted(true);
        foundCustomer.setModifiedTime(LocalDateTime.now());

        customerRepository.save(foundCustomer);

        return Optional.of(ObjectMapper.map(foundCustomer, CustomerResponse.class));
    }

    public void validateCustomerRequest(CustomerRequest customerRequest) {
        String message = objectValidator.validateRequestAndReturnMessage(customerRequest);

        if (!ObjectUtils.isEmpty(message)) {
            throw new InvalidRequestException(message);
        }

        if (customerRequest.getUsername().isEmpty()) {
            throw new InvalidRequestException("Empty Username");
        }

    }


}
