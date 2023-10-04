package personal.shopfast.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.shopfast.dao.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByPhoneNumber(String phoneNumber);
    Customer findByPhoneNumber(String phoneNumber);

    Customer findByUsername(String username);
}
