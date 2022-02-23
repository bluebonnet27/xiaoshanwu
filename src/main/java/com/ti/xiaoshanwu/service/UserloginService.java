package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Userlogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Userlogin)表服务接口
 *
 * @author makejava
 * @since 2022-02-22 12:24:12
 */
public interface UserloginService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Userlogin queryById(String userid);

    /**
     * 分页查询
     *
     * @param userlogin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Userlogin> queryByPage(Userlogin userlogin, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userlogin 实例对象
     * @return 实例对象
     */
    Userlogin insert(Userlogin userlogin);

    /**
     * 修改数据
     *
     * @param userlogin 实例对象
     * @return 实例对象
     */
    Userlogin update(Userlogin userlogin);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);

}
