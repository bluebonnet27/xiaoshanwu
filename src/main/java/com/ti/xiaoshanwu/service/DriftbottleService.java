package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Driftbottle;
import com.ti.xiaoshanwu.entity.impl.DriftbottleImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Driftbottle)表服务接口
 *
 * @author makejava
 * @since 2022-03-26 10:13:48
 */
public interface DriftbottleService {

    /**
     * 通过ID查询单条数据
     *
     * @param bottleid 主键
     * @return 实例对象
     */
    Driftbottle queryById(Integer bottleid);

    /**
     * Query random driftbottle.
     *
     * @param recordNum the record num
     * @return the driftbottle
     */
    Driftbottle queryRandom(Integer recordNum);

    /**
     * 分页查询
     *
     * @param driftbottle 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Driftbottle> queryByPage(Driftbottle driftbottle, PageRequest pageRequest);

    /**
     * 查询满足条件的漂流瓶总数.
     *
     * @param driftbottle the driftbottle
     * @return the long
     */
    long countDriftbottle(Driftbottle driftbottle);

    /**
     * Gets count.
     *
     * @param driftbottle the driftbottle
     * @return the count
     */
    long getCount(Driftbottle driftbottle);

    /**
     * 新增数据
     *
     * @param driftbottle 实例对象
     * @return 实例对象
     */
    Driftbottle insert(Driftbottle driftbottle);

    /**
     * 修改数据
     *
     * @param driftbottle 实例对象
     * @return 实例对象
     */
    Driftbottle update(Driftbottle driftbottle);

    /**
     * 通过主键删除数据
     *
     * @param bottleid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer bottleid);

    /**
     * driftbottle转换为driftbottleimpl
     *
     * @param driftbottle the driftbottle
     * @return the driftbottle
     */
    DriftbottleImpl convertToDriftbottleImpl(Driftbottle driftbottle);

}
