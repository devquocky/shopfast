package personal.shopfast.dao.meta;

import personal.shopfast.dao.entity.User;

import javax.persistence.metamodel.SingularAttribute;

public abstract class User_ {
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
}
