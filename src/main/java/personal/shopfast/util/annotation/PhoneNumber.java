package personal.shopfast.util.annotation;

import javax.validation.Constraint;
import java.lang.annotation.*;

import javax.validation.Payload;

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
