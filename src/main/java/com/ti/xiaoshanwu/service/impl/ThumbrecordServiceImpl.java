package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Thumbrecord;
import com.ti.xiaoshanwu.dao.ThumbrecordDao;
import com.ti.xiaoshanwu.service.ThumbrecordService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Thumbrecord)表服务实现类
 *
 * @author makejava
 * @since 2022-03-23 00:35:02
 */
@Service("thumbrecordService")
public class ThumbrecordServiceImpl implements ThumbrecordService {
    @Resource
    private ThumbrecordDao thumbrecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param thumbid 主键
     * @return 实例对象
     */
    @Override
    public Thumbrecord queryById(Integer thumbid) {
        return this.thumbrecordDao.queryById(thumbid);
    }

    /**
     * 分页查询
     *
     * @param thumbrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Thumbrecord> queryByPage(Thumbrecord thumbrecord, PageRequest pageRequest) {
        long total = this.thumbrecordDao.count(thumbrecord);
        return new PageImpl<>(this.thumbrecordDao.queryAllByLimit(thumbrecord, pageRequest), pageRequest, total);
    }

    @Override
    public Boolean isThumbRecordExist(Thumbrecord thumbrecord) {
        return !this.thumbrecordDao.queryAllBy4(thumbrecord).isEmpty();
    }

    /**
     * 新增数据
     *
     * @param thumbrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Thumbrecord insert(Thumbrecord thumbrecord) {
        this.thumbrecordDao.insert(thumbrecord);
        return thumbrecord;
    }

    /**
     * 修改数据
     *
     * @param thumbrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Thumbrecord update(Thumbrecord thumbrecord) {
        this.thumbrecordDao.update(thumbrecord);
        return this.queryById(thumbrecord.getThumbid());
    }

    /**
     * 通过主键删除数据
     *
     * @param thumbid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer thumbid) {
        return this.thumbrecordDao.deleteById(thumbid) > 0;
    }
}
