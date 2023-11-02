package personal.shopfast.dao.entity;

import personal.shopfast.exception.ResourceNotFoundException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber.isEmpty()) throw new ResourceNotFoundException("Empty Phone Number");
        return phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }
}
