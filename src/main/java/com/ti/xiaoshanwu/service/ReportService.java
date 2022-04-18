package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Report;
import com.ti.xiaoshanwu.entity.impl.ReportImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Report)表服务接口
 *
 * @author makejava
 * @since 2022-03-31 20:26:58
 */
public interface ReportService {

    /**
     * 通过ID查询单条数据
     *
     * @param reportid 主键
     * @return 实例对象
     */
    Report queryById(Integer reportid);

    /**
     * 分页查询
     *
     * @param report 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Report> queryByPage(Report report, PageRequest pageRequest);

    /**
     * 查询满足给定条件的举报是否存在
     *
     * @param report the report
     * @return the boolean
     */
    Boolean isReportExist(Report report);

    /**
     * 新增数据
     *
     * @param report 实例对象
     * @return 实例对象
     */
    Report insert(Report report);

    /**
     * 修改数据
     *
     * @param report 实例对象
     * @return 实例对象
     */
    Report update(Report report);

    /**
     * 通过主键删除数据
     *
     * @param reportid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer reportid);

    /**
     * 转换为implement扩展类.
     *
     * @param report the report
     * @return the report
     */
    ReportImpl convertToImpl(Report report);

}
