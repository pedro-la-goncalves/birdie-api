package com.birdie.api.booking.reservation.guest.contact.annotation;

import com.birdie.api.booking.reservation.guest.contact.ContactType;
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

        if (ContactType.EMAIL.equals(contactType)) return isEmailValid(constraintValidatorContext, contactValue);
        else if (ContactType.PHONE.equals(contactType)) return isPhoneValid(constraintValidatorContext, contactValue);
        else if (ContactType.OTHER.equals(contactType)) return isContactValid(constraintValidatorContext, contactValue);

        return false;
    }

    private boolean isEmailValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        boolean isValid =  Pattern.compile(EMAIL_VALIDATION_REGEX).matcher((CharSequence) contactValue).matches();

        if (!isValid) {
            customMessageForValidation(constraintValidatorContext, String.format("field \"value\" must have a valid value for type %s like: example@example.com", ContactType.EMAIL));
            return false;
        }

        return true;
    }

    private boolean isPhoneValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        boolean isValid =  Pattern.compile(PHONE_VALIDATION_REGEX).matcher((CharSequence) contactValue).matches();

        if (!isValid) {
            customMessageForValidation(constraintValidatorContext, String.format("field \"value\" must have a valid value for type %s like: 999999999999", ContactType.PHONE));
            return false;
        }

        return true;
    }

    private boolean isContactValid(ConstraintValidatorContext constraintValidatorContext, Object contactValue) {
        if (Objects.isNull(contactValue) || String.valueOf(contactValue).isBlank() || String.valueOf(contactValue).isEmpty()) {
            customMessageForValidation(constraintValidatorContext, String.format("field \"value\" must have a valid value for type %s", ContactType.OTHER));
            return false;
        }
        return true;
    }

    private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
