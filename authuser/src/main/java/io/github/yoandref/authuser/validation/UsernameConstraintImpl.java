package io.github.yoandref.authuser.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameConstraintImpl implements ConstraintValidator<UsernameConstraints, String> {
    @Override
    public void initialize(UsernameConstraints constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (null == username || username.trim().isEmpty() || username.contains(" ")) {
            return false;
        }
        return true;
    }
}
