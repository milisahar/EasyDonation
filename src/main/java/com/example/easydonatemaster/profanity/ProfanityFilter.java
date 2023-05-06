package com.example.easydonatemaster.profanity;
import com.example.easydonatemaster.profanity.ProfanityValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ProfanityValidator.class})

public @interface ProfanityFilter {
    String message() default "The article contains prohibited words.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ProfanityFilter[] value();
    }
}

