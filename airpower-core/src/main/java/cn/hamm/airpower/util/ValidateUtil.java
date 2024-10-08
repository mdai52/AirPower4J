package cn.hamm.airpower.util;

import cn.hamm.airpower.config.Constant;
import cn.hamm.airpower.config.PatternConstant;
import cn.hamm.airpower.enums.ServiceError;
import cn.hamm.airpower.exception.ServiceException;
import cn.hamm.airpower.root.RootModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <h1>验证器工具类</h1>
 *
 * @author Hamm.cn
 */
@Component
public class ValidateUtil {
    /**
     * <h2>验证器实例</h2>
     */
    private final Validator validator;

    public ValidateUtil() {
        // 初始化验证器工厂
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            // 创建验证器实例
            validator = validatorFactory.getValidator();
        }
    }

    /**
     * <h2>是否是数字</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isNumber(String value) {
        return validRegex(value, PatternConstant.NUMBER);
    }

    /**
     * <h2>是否是整数</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isInteger(String value) {
        return validRegex(value, PatternConstant.INTEGER);
    }

    /**
     * <h2>是否是邮箱</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isEmail(String value) {
        return validRegex(value, PatternConstant.EMAIL);
    }

    /**
     * <h2>是否是字母</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isLetter(String value) {
        return validRegex(value, PatternConstant.LETTER);
    }

    /**
     * <h2>是否是字母+数字</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isLetterOrNumber(String value) {
        return validRegex(value, PatternConstant.LETTER_OR_NUMBER);
    }

    /**
     * <h2>是否是中文汉字</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isChinese(String value) {
        return validRegex(value, PatternConstant.CHINESE);
    }

    /**
     * <h2>是否是手机号</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isMobilePhone(String value) {
        return validRegex(value, PatternConstant.MOBILE_PHONE);
    }

    /**
     * <h2>是否是座机电话</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isTelPhone(String value) {
        return validRegex(value, PatternConstant.TEL_PHONE);
    }

    /**
     * <h2>是否是普通字符</h2>
     * 允许字符:
     * <p>
     * {@code @ # % a-z A-Z 0-9 汉字 _ + /}
     * </p>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isNormalCode(String value) {
        return validRegex(value, PatternConstant.NORMAL_CODE);
    }

    /**
     * <h2>是否是纯字母和数字</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isOnlyNumberAndLetter(String value) {
        return validRegex(value, PatternConstant.NUMBER_OR_LETTER);
    }

    /**
     * <h2>是否是自然数</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isNaturalNumber(String value) {
        return validRegex(value, PatternConstant.NATURAL_NUMBER);
    }

    /**
     * <h2>是否是自然整数</h2>
     *
     * @param value 参数
     * @return 验证结果
     */
    public final boolean isNaturalInteger(String value) {
        return validRegex(value, PatternConstant.NATURAL_INTEGER);
    }

    /**
     * <h2>是否是有效二代身份证号</h2>
     *
     * @param idCard 身份证号
     * @return 验证结果
     */
    public final boolean isChina2Identity(String idCard) {
        // 一代身份证长度
        final int idLength = 15;
        // 二代身份证长度
        final int id2Length = 18;
        // 二代身份证求余数
        final int id2Mod = 11;
        // 系数
        final int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 尾数
        final char[] flags = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        if (Objects.isNull(idCard)) {
            return false;
        }
        if (idCard.length() != id2Length && idCard.length() != idLength) {
            return false;
        }
        if (idCard.length() == id2Length) {
            // 校验二代身份证
            int sum = 0;
            for (int i = 0; i < idCard.length() - 1; i++) {
                sum += Integer.parseInt(String.valueOf(idCard.charAt(i))) * factor[i];
            }

            // 求和后取余数11，得到的余数与校验码进行匹配，匹配成功，说明通过验证。
            return flags[sum % id2Mod] == idCard.charAt(idCard.length() - 1);
        }
        throw new ServiceException("暂不支持一代身份证校验");
    }

    /**
     * <h2>正则校验</h2>
     *
     * @param value   参数
     * @param pattern 正则
     * @return 验证结果
     */
    public final boolean validRegex(String value, @NotNull Pattern pattern) {
        return pattern.matcher(value).matches();
    }

    /**
     * <h2>验证传入的数据模型</h2>
     *
     * @param model   数据模型
     * @param actions {@code 可选} 校验分组
     * @param <M>     模型类型
     */
    public final <M extends RootModel<M>> void valid(M model, Class<?>... actions) {
        if (Objects.isNull(model)) {
            return;
        }
        if (actions.length == Constant.ZERO_INT) {
            Set<ConstraintViolation<M>> violations = validator.validate(model);
            if (violations.isEmpty()) {
                return;
            }
            ServiceError.PARAM_INVALID.show(violations.iterator().next().getMessage());
        }
        Set<ConstraintViolation<M>> violations = validator.validate(model, actions);
        if (violations.isEmpty()) {
            return;
        }
        ServiceError.PARAM_INVALID.show(violations.iterator().next().getMessage());
    }
}
