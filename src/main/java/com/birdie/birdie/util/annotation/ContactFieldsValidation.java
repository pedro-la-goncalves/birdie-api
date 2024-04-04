package com.birdie.birdie.util.annotation;

import com.birdie.birdie.booking.guest.contact.EContactType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;
import java.util.regex.Pattern;

public class ContactFieldsValidation implements ConstraintValidator<ContactFields, Object> {

    private final String EMAIL_VALIDATION_REGEX = "^(.+)@(.+)$";
    private final String PHONE_VALIDATION_REGEX = "(^$|[0-9]{10,15})";

    @Override
    public boolean isValid(Object contact, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        Object contactType = new BeanWrapperImpl(contact).getPropertyValue("type");
        Object contactValue = new BeanWrapperImpl(contact).getPropertyValue("value");

        if (EContactType.EMAIL.equals(contactType)) return isEmailValid(constraintValidatorContext, contactValue);
        else if (EContactType.PHONE.equals(contactType)) return isPhoneValid(constraintValidatorContext, contactValue);
        else if (EContactType.OTHER.equals(contactType)) return isContactValid(constraintValidatorContext, contactValue);

        return false;
    }

    private boolean isEmailValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        boolean isValid =  Pattern.compile(EMAIL_VALIDATION_REGEX).matcher((CharSequence) contactValue).matches();

        if (!isValid) {
            customMessageForValidation(constraintValidatorContext, String.format("campo \"value\" deve conter um valor válido para o tipo %s, como: exemplo@exemplo.com", EContactType.EMAIL));
            return false;
        }

        return true;
    }

    private boolean isPhoneValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        boolean isValid =  Pattern.compile(PHONE_VALIDATION_REGEX).matcher((CharSequence) contactValue).matches();

        if (!isValid) {
            customMessageForValidation(constraintValidatorContext, String.format("campo \"value\" deve conter um valor válido para o tipo %s, como: 047999999999", EContactType.PHONE));
            return false;
        }

        return true;
    }

    private boolean isContactValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        if (Objects.isNull(contactValue) || String.valueOf(contactValue).isBlank() || String.valueOf(contactValue).isEmpty()) {
            customMessageForValidation(constraintValidatorContext, String.format("valor inválido para contato do tipo %s", EContactType.OTHER));
            return false;
        }
        return true;
    }

    private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
