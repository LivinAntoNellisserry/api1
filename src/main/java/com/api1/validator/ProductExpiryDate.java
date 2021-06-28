package com.api1.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductExpiryDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductExpiryDate {
    String message() default "Enter a valid expiry date in YYYY-MM-DD format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
