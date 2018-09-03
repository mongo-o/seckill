package com.ayl.seckil.validator;

import com.ayl.seckil.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author AYL    2018/9/3 11:27
 */
public class PhoneValidator implements ConstraintValidator<IsPhone, String> {
    private boolean required = true;

    @Override
    public void initialize(IsPhone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            if (StringUtils.isEmpty(s)) {
                return false;
            } else {
                return ValidatorUtil.isMobilePhone(s);
            }
        }
        return false;
    }
}
