package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Report;
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

}
