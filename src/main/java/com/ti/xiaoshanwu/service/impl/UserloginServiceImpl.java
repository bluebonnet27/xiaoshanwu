package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Userlogin;
import com.ti.xiaoshanwu.dao.UserloginDao;
import com.ti.xiaoshanwu.service.UserloginService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Userlogin)表服务实现类
 *
 * @author makejava
 * @since 2022-02-22 12:55:08
 */
@Service("userloginService")
public class UserloginServiceImpl implements UserloginService {
    @Resource
    private UserloginDao userloginDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public Userlogin queryById(String userid) {
        return this.userloginDao.queryById(userid);
    }

    /**
     * 分页查询
     *
     * @param userlogin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Userlogin> queryByPage(Userlogin userlogin, PageRequest pageRequest) {
        long total = this.userloginDao.count(userlogin);
        return new PageImpl<>(this.userloginDao.queryAllByLimit(userlogin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param userlogin 实例对象
     * @return 实例对象
     */
    @Override
    public Userlogin insert(Userlogin userlogin) {
        this.userloginDao.insert(userlogin);
        return userlogin;
    }

    /**
     * 修改数据
     *
     * @param userlogin 实例对象
     * @return 实例对象
     */
    @Override
    public Userlogin update(Userlogin userlogin) {
        this.userloginDao.update(userlogin);
        return this.queryById(userlogin.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.userloginDao.deleteById(userid) > 0;
    }
}
