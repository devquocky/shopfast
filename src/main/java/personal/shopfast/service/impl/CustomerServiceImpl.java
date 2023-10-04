package personal.shopfast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import personal.shopfast.dao.entity.Customer;
import personal.shopfast.dao.repository.CustomerRepository;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.dto.response.CustomerResponse;
import personal.shopfast.exception.DuplicateResourceException;
import personal.shopfast.exception.ResourceNotFoundException;
import personal.shopfast.service.AbstractService;
import personal.shopfast.service.CustomerService;
import personal.shopfast.util.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Optional<List<CustomerResponse>> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> customersResponse = customers.stream().map(driver ->
                ObjectMapper.map(driver, CustomerResponse.class)
        ).collect(Collectors.toList());

        return Optional.of(customersResponse);
    }

    @Override
    public Optional<CustomerResponse> getCustomerById(int customerId) {
        Customer foundCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                new ResourceNotFoundException("This customer not exist"));

        if (foundCustomer.isDeleted()) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        CustomerResponse customerResponse = ObjectMapper.map(foundCustomer, CustomerResponse.class);

        return Optional.of(customerResponse);
    }

    @Override
    public Optional<CustomerResponse> getCustomerByPhoneNumber(String phoneNumber) {
        Customer foundCustomer = customerRepository.findByPhoneNumber(phoneNumber);

        if (foundCustomer.isDeleted() || ObjectUtils.isEmpty(foundCustomer)) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        CustomerResponse customerResponse = ObjectMapper.map(foundCustomer, CustomerResponse.class);

        return Optional.of(customerResponse);
    }

    @Override
    public Optional<CustomerResponse> getCustomerByUsername(String username) {
        Customer foundCustomer = customerRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(foundCustomer)) {
            throw new ResourceNotFoundException("This customer not exist");
        }
        if (foundCustomer.isDeleted()) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        CustomerResponse customerResponse = ObjectMapper.map(foundCustomer, CustomerResponse.class);

        return Optional.of(customerResponse);
    }

    @Override
    public Optional<CustomerResponse> addNewCustomer(CustomerRequest customerRequest) {
        Customer foundCustomerByUsername = customerRepository.findByUsername(customerRequest.getUsername());
        if (!ObjectUtils.isEmpty(foundCustomerByUsername)) {
            throw new DuplicateResourceException("This username is existed");
        }

        Customer foundCustomerByPhoneNumber = customerRepository.findByPhoneNumber(customerRequest.getPhoneNumber());
        if (!ObjectUtils.isEmpty(foundCustomerByPhoneNumber)) {
            throw new DuplicateResourceException("This phone number is used for another user");
        }

        Customer newCustomer = ObjectMapper.map(customerRequest, Customer.class);
//        newCustomer.setCreateTime(LocalDateTime.now());

        customerRepository.save(newCustomer);

        return Optional.of(ObjectMapper.map(newCustomer, CustomerResponse.class));
    }

    @Override
    public Optional<CustomerResponse> updateCustomer(CustomerRequest customerRequest) {
        Customer foundCustomer = customerRepository.findByUsername(customerRequest.getUsername());

        if (ObjectUtils.isEmpty(foundCustomer)) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        if (foundCustomer.isDeleted()) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        Customer newModifiedCustomer = ObjectMapper.map(customerRequest, Customer.class);
        newModifiedCustomer.setCustomerId(foundCustomer.getCustomerId());
        newModifiedCustomer.setModifiedTime(LocalDateTime.now());
        customerRepository.save(newModifiedCustomer);

        return Optional.of(ObjectMapper.map(foundCustomer, CustomerResponse.class));
    }

    @Override
    public Optional<CustomerResponse> deleteCustomer(String username) {
        Customer foundCustomer = customerRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(foundCustomer)) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        if (foundCustomer.isDeleted()) {
            throw new ResourceNotFoundException("This customer not exist");
        }

        foundCustomer.setDeleted(true);
        foundCustomer.setModifiedTime(LocalDateTime.now());

        customerRepository.save(foundCustomer);

        return Optional.of(ObjectMapper.map(foundCustomer, CustomerResponse.class));
    }

}
