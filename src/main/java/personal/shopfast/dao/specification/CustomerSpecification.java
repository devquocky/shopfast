package personal.shopfast.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import personal.shopfast.dao.entity.Customer;
import personal.shopfast.dao.meta.Customer_;

public final class CustomerSpecification {

    public static Specification<Customer> hasId(int customerId) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.ID), customerId);
    }

    public static Specification<Customer> hasUsername(String username) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.USERNAME), username);
    }

    public static Specification<Customer> likeUsername(String username) {
        return (root, query, cb) -> cb.like(root.get(Customer_.USERNAME), "%" + username + "%");
    }

    public static Specification<Customer> likePhoneNumber(String phoneNumber) {
        return (root, query, cb) -> cb.like(root.get(Customer_.USERNAME), "%" + phoneNumber + "%");
    }

    public static Specification<Customer> isActive() {
        return (root, query, cb) -> cb.equal(root.get(Customer_.DELETED), false);
    }

//    public static Specification<User> hasIdIn(Collection<Long> userIds) {
//        return (root, query, cb) -> root.get(Customer_.ID).in(userIds);
//    }
}
