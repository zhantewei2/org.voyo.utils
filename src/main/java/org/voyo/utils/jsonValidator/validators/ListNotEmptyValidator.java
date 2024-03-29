package org.voyo.utils.jsonValidator.validators;

import org.voyo.utils.jsonValidator.ListNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListNotEmptyValidator implements ConstraintValidator<ListNotEmpty, List<?>> {
    private String message;
    @Override
    public void initialize(ListNotEmpty listNotEmpty){
        message= listNotEmpty.message();
    }
    @Override
    public boolean isValid(List<?> val, ConstraintValidatorContext ctx){
        return val!=null && !val.isEmpty();
    }
}
