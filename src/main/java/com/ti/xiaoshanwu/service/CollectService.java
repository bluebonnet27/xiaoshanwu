package com.ti.xiaoshanwu.service;
import com.ti.xiaoshanwu.entity.Collect;
import com.ti.xiaoshanwu.entity.impl.CollectImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Collect)表服务接口
 *
 * @author makejava
 * @since 2022-03-22 22:11:22
 */
public interface CollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param collectid 主键
     * @return 实例对象
     */
    Collect queryById(Integer collectid);

    /**
     * 分页查询
     *
     * @param collect 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Collect> queryByPage(Collect collect, PageRequest pageRequest);

    /**
     * 不分页查询
     *
     * @param collect 筛选条件
     * @return 查询结果
     */
    List<Collect> queryByNoPage(Collect collect);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect insert(Collect collect);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect update(Collect collect);

    /**
     * 通过主键删除数据
     *
     * @param collectid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer collectid);

    /**
     * Convert to collect collect.
     *
     * @param collect the collect
     * @return the collect
     */
    CollectImpl convertToCollectImpl(Collect collect);

    /**
     * Is collect existed boolean.
     *
     * @param collect the collect
     * @return the boolean
     */
    Boolean isCollectExisted(Collect collect);

}
