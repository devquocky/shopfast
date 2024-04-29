package personal.shopfast.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import personal.shopfast.dao.entity.User;
import personal.shopfast.dao.meta.User_;

public final class UserSpecification {

    public static Specification<User> hasId(int customerId) {
        return (root, query, cb) -> cb.equal(root.get(User_.ID), customerId);
    }

    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) -> cb.equal(root.get(User_.USERNAME), username);
    }

    public static Specification<User> likeUsername(String username) {
        return (root, query, cb) -> cb.like(root.get(User_.USERNAME), "%" + username + "%");
    }

}
