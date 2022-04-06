package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Loginrecord;
import com.ti.xiaoshanwu.entity.impl.LoginrecordImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Loginrecord)表服务接口
 *
 * @author makejava
 * @since 2022-04-06 10:28:44
 */
public interface LoginrecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param loginrecordid 主键
     * @return 实例对象
     */
    Loginrecord queryById(Integer loginrecordid);

    /**
     * 分页查询
     *
     * @param loginrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Loginrecord> queryByPage(Loginrecord loginrecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param loginrecord 实例对象
     * @return 实例对象
     */
    Loginrecord insert(Loginrecord loginrecord);

    /**
     * 修改数据
     *
     * @param loginrecord 实例对象
     * @return 实例对象
     */
    Loginrecord update(Loginrecord loginrecord);

    /**
     * 通过主键删除数据
     *
     * @param loginrecordid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer loginrecordid);

    /**
     * 转换为impl扩展.
     *
     * @param loginrecord the loginrecord
     * @return the loginrecord
     */
    LoginrecordImpl convertToLoginrecordImpl(Loginrecord loginrecord);

}
