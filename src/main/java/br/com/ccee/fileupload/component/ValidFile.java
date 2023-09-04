package br.com.ccee.fileupload.component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileXmlValidator.class})
public @interface ValidFile {
    String message() default "Somente arquivos XML permitidos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
