package org.voyo.utils.jsonValidator.validators;

import org.voyo.utils.jsonValidator.ListNotEmpty;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.List;

public class ListNotEmptyValidator implements ConstraintValidator<ListNotEmpty, Collection<?>> {
    private String message;
    @Override
    public void initialize(ListNotEmpty listNotEmpty){
        message= listNotEmpty.message();
    }
    @Override
    public boolean isValid(Collection<?> val, ConstraintValidatorContext ctx){
        return val!=null && !val.isEmpty();
    }
}
