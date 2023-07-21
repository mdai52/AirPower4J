package cn.hamm.airpower.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h1>自定义异常包装类</h1>
 *
 * @author Hamm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultException extends RuntimeException implements IResult {
    /**
     * <h1>错误代码</h1>
     */
    private int code = Result.ERROR.getCode();

    /**
     * <h1>错误信息</h1>
     */
    private String message = Result.ERROR.getMessage();

    /**
     * <h1>抛出一个自定义错误信息的默认异常</h1>
     *
     * @param message 错误信息
     */
    public ResultException(String message) {
        this.setCode(Result.ERROR.getCode()).setMessage(message);
    }

    /**
     * <h1>直接抛出一个异常</h1>
     *
     * @param error 异常
     */
    public ResultException(Result error) {
        this.setCode(error.getCode()).setMessage(error.getMessage());
    }

    /**
     * <h1>直接抛出一个异常</h1>
     *
     * @param error   异常
     * @param message 错误信息
     */
    public ResultException(Result error, String message) {
        this.setCode(error.getCode()).setMessage(message);
    }
}
