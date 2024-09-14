package cn.hamm.airpower.model.query;

import cn.hamm.airpower.annotation.Description;
import cn.hamm.airpower.root.RootModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h1>查询导出结果模型</h1>
 *
 * @author Hamm.cn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryExport extends RootModel {
    @NotBlank(message = "文件Code不能为空")
    @Description("文件Code")
    private String fileCode;
}
