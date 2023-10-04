package personal.shopfast.service;


import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    Optional<List<CustomerResponse>> getAllCustomer();

    Optional<CustomerResponse> getCustomerById(int customerId);

    Optional<CustomerResponse> getCustomerByPhoneNumber(String phoneNumber);

    Optional<CustomerResponse> getCustomerByUsername(String username);

    Optional<CustomerResponse> addNewCustomer(CustomerRequest customerRequest);

    Optional<CustomerResponse> updateCustomer(CustomerRequest customerRequest);

    Optional<CustomerResponse> deleteCustomer(String username);
}
