package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Notice;
import com.ti.xiaoshanwu.dao.NoticeDao;
import com.ti.xiaoshanwu.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Notice)表服务实现类
 *
 * @author makejava
 * @since 2022-04-15 10:12:39
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param noticeid 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer noticeid) {
        return this.noticeDao.queryById(noticeid);
    }

    /**
     * 分页查询
     *
     * @param notice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Notice> queryByPage(Notice notice, PageRequest pageRequest) {
        long total = this.noticeDao.count(notice);
        return new PageImpl<>(this.noticeDao.queryAllByLimit(notice, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice insert(Notice notice) {
        this.noticeDao.insert(notice);
        return notice;
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice update(Notice notice) {
        this.noticeDao.update(notice);
        return this.queryById(notice.getNoticeid());
    }

    /**
     * 通过主键删除数据
     *
     * @param noticeid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer noticeid) {
        return this.noticeDao.deleteById(noticeid) > 0;
    }
}
