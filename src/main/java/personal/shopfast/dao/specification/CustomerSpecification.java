package personal.shopfast.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import personal.shopfast.dao.entity.Customer;
import personal.shopfast.dao.meta.Customer_;
import personal.shopfast.dto.filter.CustomerFilter;

import java.util.ArrayList;
import java.util.List;

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

    private Specification<Customer> createSpecification(CustomerFilter input) {
        switch (input.getOperator()) {

            case "EQUALS":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case "NOT_EQUALS":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case "GREATER_THAN":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case "LESS_THAN":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case "LIKE":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()),
                                "%" + input.getValue() + "%");

            case "IN":
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValues()));

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
