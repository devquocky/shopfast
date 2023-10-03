package personal.shopfast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.shopfast.dao.entity.Customer;
import personal.shopfast.dao.repository.CustomerRepository;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.dto.response.CustomerResponse;
import personal.shopfast.exception.ResourceNotFoundException;
import personal.shopfast.service.AbstractService;
import personal.shopfast.service.CustomerService;

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
                new personal.shopfast.dto.response.CustomerResponse(
                        driver.getUsername(), driver.getFirstName(), driver.getLastName()
                        , driver.getPhoneNumber()
                        , driver.getCreateTime(), driver.getModifiedTime(),
                        driver.isDeleted()
                )
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

        CustomerResponse customerResponse = CustomerResponse.builder()
                .username(foundCustomer.getUsername())
                .firstName(foundCustomer.getFirstName())
                .lastName(foundCustomer.getLastName())
                .phoneNumber(foundCustomer.getPhoneNumber())
                .createTime(foundCustomer.getCreateTime())
                .modifiedTime(foundCustomer.getModifiedTime())
                .build();

        return Optional.of(customerResponse);
    }

    @Override
    public Optional<List<CustomerResponse>> getCustomerByPhoneNumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerResponse> addNewCustomer(CustomerRequest customerRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerResponse> updateCustomer(CustomerRequest customerRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerResponse> deleteCustomer(String username) {
        return Optional.empty();
    }

}
