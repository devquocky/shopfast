package personal.shopfast.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ObjectValidator {

    @Autowired
    @Qualifier("validator")
    LocalValidatorFactoryBean validatorFactoryBean;

    public <T> String validateRequestAndReturnMessage(T t) {
        Set<ConstraintViolation<T>> violations = validatorFactoryBean.getValidator().validate(t);
        List<String> mess = new ArrayList<>();
        for (ConstraintViolation<T> violation : violations) {
            mess.add(violation.getMessage());
        }

        String text = String.join(", ", mess);
        if (text.contains(", ")) {
            text = "Error: " + text;
        }
        return text;
    }

}
