package cn.hamm.airpower.validate.dictionary;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;

/**
 * <h1>枚举字典验证实现类</h1>
 *
 * @author Hamm
 */
public class DictionaryAnnotationValidator implements ConstraintValidator<Dictionary, Integer> {
    Class<?> enumClazz = null;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        boolean isValidated = false;
        try {
            Method getKey = enumClazz.getMethod("getKey");
            //取出所有枚举类型
            Object[] objs = enumClazz.getEnumConstants();
            for (Object obj : objs) {
                if (value.equals(getKey.invoke(obj))) {
                    isValidated = true;
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        return isValidated;
    }

    @Override
    public void initialize(Dictionary constraintAnnotation) {
        enumClazz = constraintAnnotation.value();
    }
}