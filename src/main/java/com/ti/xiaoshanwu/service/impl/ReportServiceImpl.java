package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Report;
import com.ti.xiaoshanwu.dao.ReportDao;
import com.ti.xiaoshanwu.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Report)表服务实现类
 *
 * @author makejava
 * @since 2022-03-31 20:26:58
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportDao reportDao;

    /**
     * 通过ID查询单条数据
     *
     * @param reportid 主键
     * @return 实例对象
     */
    @Override
    public Report queryById(Integer reportid) {
        return this.reportDao.queryById(reportid);
    }

    /**
     * 分页查询
     *
     * @param report 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Report> queryByPage(Report report, PageRequest pageRequest) {
        long total = this.reportDao.count(report);
        return new PageImpl<>(this.reportDao.queryAllByLimit(report, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param report 实例对象
     * @return 实例对象
     */
    @Override
    public Report insert(Report report) {
        this.reportDao.insert(report);
        return report;
    }

    /**
     * 修改数据
     *
     * @param report 实例对象
     * @return 实例对象
     */
    @Override
    public Report update(Report report) {
        this.reportDao.update(report);
        return this.queryById(report.getReportid());
    }

    /**
     * 通过主键删除数据
     *
     * @param reportid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer reportid) {
        return this.reportDao.deleteById(reportid) > 0;
    }
}
