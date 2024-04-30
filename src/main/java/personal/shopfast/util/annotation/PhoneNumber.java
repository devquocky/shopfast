package personal.shopfast.util.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({PARAMETER, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "This phone number is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
