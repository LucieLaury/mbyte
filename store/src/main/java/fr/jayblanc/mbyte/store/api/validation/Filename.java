package fr.jayblanc.mbyte.store.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy={})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp=ValidationPattern.FILE_PATTERN)
public @interface Filename {
    String message() default "{invalid.filename}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
