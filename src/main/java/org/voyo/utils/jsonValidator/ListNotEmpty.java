package org.voyo.utils.jsonValidator;

import org.voyo.utils.jsonValidator.validators.ListNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ListNotEmptyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListNotEmpty {
    String message() default "列表不可为空"   ;
    Class<?>[] groups() default {};
    int[] values() default {};
    Class<? extends Payload>[] payload() default {};
}
