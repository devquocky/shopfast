package personal.shopfast.service;


import org.springframework.data.domain.PageRequest;
import personal.shopfast.dto.request.CustomerRequest;
import personal.shopfast.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    Optional<List<CustomerResponse>> getAllCustomer(PageRequest pageRequest);

    Optional<CustomerResponse> getCustomerById(int customerId);

    Optional<List<CustomerResponse>> getCustomersByPhoneNumber(String phoneNumber, PageRequest pageRequest);

    Optional<List<CustomerResponse>> getCustomersByUsername(String username, PageRequest pageRequest);

    Optional<CustomerResponse> addNewCustomer(CustomerRequest customerRequest);

    Optional<CustomerResponse> updateCustomer(CustomerRequest customerRequest);

    Optional<CustomerResponse> deleteCustomer(String username);
}
