package cn.hamm.airpower.model.query;

import cn.hamm.airpower.annotation.Description;
import cn.hamm.airpower.model.Sort;
import cn.hamm.airpower.root.RootModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h1>查询列表请求</h1>
 *
 * @param <M> 数据模型
 * @author Hamm.cn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Description("查询列表请求")
public class QueryListRequest<M extends RootModel<M>> extends QueryRequest<M> {
    /**
     * <h3>排序对象</h3>
     */
    @Description("排序对象")
    private Sort sort = null;
}
