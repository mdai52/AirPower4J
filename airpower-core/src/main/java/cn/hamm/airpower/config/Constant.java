package cn.hamm.airpower.config;

import org.springframework.http.HttpHeaders;

/**
 * <h1>内置常量</h1>
 *
 * @author Hamm.cn
 */
public class Constant {
    /**
     * <h2>AirPower</h2>
     */
    public static final String AIRPOWER = "airpower";

    /**
     * <h2>创建时间字段名</h2>
     */
    public static final String CREATE_TIME_FIELD = "createTime";

    /**
     * <h2>修改时间字段名</h2>
     */
    public static final String UPDATE_TIME_FIELD = "updateTime";

    /**
     * <h2>主键ID字段名</h2>
     */
    public static final String ID_FIELD = "id";

    /**
     * <h2>排序降序</h2>
     */
    public static final String SORT_DESC = "desc";

    /**
     * <h2>排序升序</h2>
     */
    public static final String SORT_ASC = "asc";

    /**
     * <h2>SQL语句中like的前缀</h2>
     */
    public static final String SQL_LIKE_PERCENT = "%";

    /**
     * <h2>一分钟</h2>
     */
    public static final int SECOND_PER_MINUTE = 60;

    /**
     * <h2>一天24小时</h2>
     */
    public static final int HOUR_PER_DAY = 24;

    /**
     * <h2>一周7天</h2>
     */
    public static final int DAY_PER_WEEK = 7;

    /**
     * <h2>一小时的秒数</h2>
     */
    public static final int SECOND_PER_HOUR = SECOND_PER_MINUTE * SECOND_PER_MINUTE;

    /**
     * <h2>一天的秒数</h2>
     */
    public static final int SECOND_PER_DAY = SECOND_PER_HOUR * HOUR_PER_DAY;

    /**
     * <h2>本机IP地址</h2>
     */
    public static final String LOCAL_IP_ADDRESS = "127.0.0.1";

    /**
     * <h2>AUTHORIZATION</h2>
     */
    public static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;

    /**
     * <h2>租户编码</h2>
     */
    public static final String TENANT_CODE = "tenant-code";

    /**
     * <h2>ROOT</h2>
     */
    public static final String ROOT = "root";

    /**
     * <h2>MySQL默认端口</h2>
     */
    public static final int MYSQL_DEFAULT_PORT = 3306;

    /**
     * <h2>空字符串</h2>
     */
    public static final String EMPTY_STRING = "";
}
