package cn.hamm.airpower.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h3>格式化模板</h3>
 *
 * @author Hamm.cn
 */
@Getter
@AllArgsConstructor
public enum DateTimeFormatter {
    /**
     * <h3>年</h3>
     */
    YEAR("yyyy"),

    /**
     * <h3>月</h3>
     */
    MONTH("MM"),

    /**
     * <h3>日</h3>
     */
    DAY("dd"),

    /**
     * <h3>时</h3>
     */
    HOUR("HH"),

    /**
     * <h3>分</h3>
     */
    MINUTE("mm"),

    /**
     * <h3>秒</h3>
     */
    SECOND("ss"),

    /**
     * <h3>年月日</h3>
     */
    FULL_DATE("yyyy-MM-dd"),

    /**
     * <h3>时分秒</h3>
     */
    FULL_TIME("HH:mm:ss"),

    /**
     * <h3>年月日时分秒</h3>
     */
    FULL_DATETIME("yyyy-MM-dd HH:mm:ss"),

    /**
     * <h3>月日时分</h3>
     */
    SHORT_DATETIME("MM-dd HH:mm"),
    ;

    private final String value;
}
