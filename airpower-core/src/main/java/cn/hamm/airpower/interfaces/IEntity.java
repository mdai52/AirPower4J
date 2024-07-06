package cn.hamm.airpower.interfaces;

/**
 * <h1>标准实体接口</h1>
 *
 * @author Hamm.cn
 */
public interface IEntity<E extends IEntity<E>> {
    /**
     * <h2>获取主键 <code>ID</code></h2>
     *
     * @return 主键 <code>ID</code>
     */
    Long getId();

    /**
     * <h2>设置实体主键 <code>ID</code></h2>
     *
     * @param id 主键 <code>ID</code>
     * @return 实体
     */
    E setId(Long id);
}
