package personal.shopfast.dao.meta;


import personal.shopfast.dao.entity.Customer;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {
    public static volatile SingularAttribute<Customer, Integer> customerId;
    public static volatile SingularAttribute<Customer, String> username;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> firstname;
    public static volatile SingularAttribute<Customer, String> lastname;
    public static volatile SingularAttribute<Customer, String> phoneNumber;
    public static volatile SingularAttribute<Customer, LocalDateTime> createTime;
    public static volatile SingularAttribute<Customer, LocalDateTime> modifiedTime;
    public static volatile SingularAttribute<Customer, Boolean> isDeleted;

    public static final String ID = "customerId";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PHONENUMBER = "phoneNumber";
    public static final String CREATE_TIME = "createdTime";
    public static final String MODIFIED_TIME = "modifiedTime";
    public static final String DELETED = "isDeleted";
}
