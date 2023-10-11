package personal.shopfast.dao.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import personal.shopfast.dao.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer.customer c WHERE c.username LIKE ?1 OR c.telephone LIKE ?2", nativeQuery = true)
    List<Customer> findCustomerWithUsernameOrPhoneNumber(String username, String phoneNumber);

    //SELECT * FROM CUSTOMER c WHERE c.is_deleted = False
    List<Customer> findByIsDeletedFalse(Pageable pageable);

    // SELECT * FROM CUSTOMER c WHERE c.telephone = [phoneNumber] AND c.is_deleted = False
    Customer findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    // SELECT * FROM CUSTOMER c WHERE c.username = [username] AND c.is_deleted = False
    Customer findByUsernameAndIsDeletedFalse(String username);

    // SELECT * FROM CUSTOMER c WHERE CONTAINS(c.username , [username]) AND c.is_deleted = False
    List<Customer> findByPhoneNumberContainsAndIsDeletedFalse(String phoneNumber, Pageable pageable);

    // SELECT * FROM CUSTOMER c WHERE CONTAINS(c.username , [username]) AND c.is_deleted = False
    List<Customer> findByUsernameContainsAndIsDeletedFalse(String username, Pageable pageable);
}
