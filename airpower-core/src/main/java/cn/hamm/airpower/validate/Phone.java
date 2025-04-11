package cn.hamm.airpower.validate;

import cn.hamm.airpower.util.ValidateUtil;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h1>标记电话验证 座机或手机</h1>
 *
 * @author Hamm.cn
 * @apiNote 请注意，请自行做非空验证
 */
@Constraint(validatedBy = Phone.PhoneValidator.class)
@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Documented
public @interface Phone {
    /**
     * <h3>错误信息</h3>
     */
    String message() default "不是有效的电话号码";

    /**
     * <h3>验证组</h3>
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * <h3>是否允许手机号格式</h3>
     */
    boolean mobile() default true;

    /**
     * <h3>是否允许座机电话格式</h3>
     */
    boolean tel() default true;

    /**
     * <h1>电话验证实现类</h1>
     *
     * @author Hamm.cn
     */
    @Component
    class PhoneValidator implements ConstraintValidator<Phone, String> {
        /**
         * <h3>是否座机</h3>
         */
        private boolean tel = true;

        /**
         * <h3>是否手机号</h3>
         */
        private boolean mobile = true;

        /**
         * <h3>验证</h3>
         *
         * @param value   验证的值
         * @param context 验证会话
         * @return 验证结果
         */
        @Override
        public final boolean isValid(String value, ConstraintValidatorContext context) {
            if (!StringUtils.hasLength(value)) {
                return true;
            }
            if (!mobile && !tel) {
                // 不允许座机也不允许手机 验证个鬼啊
                return true;
            }
            if (!mobile) {
                // 只允许座机
                return ValidateUtil.isTelPhone(value);
            }
            if (!tel) {
                // 只允许手机
                return ValidateUtil.isMobilePhone(value);
            }
            // 手机座机均可
            return ValidateUtil.isMobilePhone(value) || ValidateUtil.isTelPhone(value);
        }

        /**
         * <h3>初始化</h3>
         *
         * @param phone 电话验证注解
         */
        @Override
        public final void initialize(@NotNull Phone phone) {
            mobile = phone.mobile();
            tel = phone.tel();
        }
    }

}