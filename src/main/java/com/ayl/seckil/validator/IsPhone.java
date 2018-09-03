package com.ayl.seckil.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author AYL    2018/9/3 11:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = PhoneValidator.class)
public @interface IsPhone {
    boolean required() default true;
    String message() default "电话号码格式错误";

    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
