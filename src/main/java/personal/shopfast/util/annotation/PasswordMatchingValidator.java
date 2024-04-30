package personal.shopfast.util.annotation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {
    private String newPassword;
    private String oldPassword;

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
        this.newPassword = constraintAnnotation.newPassword();
        this.oldPassword = constraintAnnotation.oldPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object newPasswordValue = new BeanWrapperImpl(value).getPropertyValue(newPassword);
        Object oldPasswordValue = new BeanWrapperImpl(value).getPropertyValue(oldPassword);

        return (newPasswordValue != null) ? newPasswordValue.equals(oldPasswordValue) : oldPasswordValue == null;
    }
}
