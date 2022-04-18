package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Report;
import com.ti.xiaoshanwu.dao.ReportDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ReportImpl;
import com.ti.xiaoshanwu.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.springframework.beans.BeanUtils.copyProperties;

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

    @Resource
    private UserDao userDao;

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
     * 查询满足给定条件的举报是否存在
     *
     * @param report the report
     * @return the boolean
     */
    @Override
    public Boolean isReportExist(Report report) {
        return this.reportDao.count(report) != 0;
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

    /**
     * 转换为implement扩展类.
     *
     * @param report the report
     * @return the report
     */
    @Override
    public ReportImpl convertToImpl(Report report) {
        ReportImpl reportImpl = new ReportImpl();
        copyProperties(report,reportImpl);

//        private String reporttimeImpl;
//
//        private String reporttypeImpl;
//
//        private String reportuseridNameImpl;
//
//        private String reportstateImpl;
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String reportTime = df.format(report.getReporttime());
        reportImpl.setReporttimeImpl(reportTime);

        String reportType;
        switch (report.getReporttype()){
            case 0:
                reportType = "帖子";
                break;
            case 1:
                reportType = "评论";
                break;
            case 2:
                reportType = "漂流瓶";
                break;
            default:
                reportType = "未知类型";
                break;
        }
        reportImpl.setReporttypeImpl(reportType);

        User user = this.userDao.queryById(report.getReportuserid());
        reportImpl.setReportuseridNameImpl(user.getUsername());

        String reportState;
        switch (report.getReportstate()){
            case 0:
                reportState = "待处理";
                break;
            case 1:
                reportState = "已处理";
                break;
            case 2:
                reportState = "已驳回";
                break;
            default:
                reportState = "未知状态";
                break;
        }
        reportImpl.setReportstateImpl(reportState);

        return reportImpl;
    }
}
