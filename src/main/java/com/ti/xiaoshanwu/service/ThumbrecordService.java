package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Thumbrecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Thumbrecord)表服务接口
 *
 * @author makejava
 * @since 2022-03-23 00:35:02
 */
public interface ThumbrecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param thumbid 主键
     * @return 实例对象
     */
    Thumbrecord queryById(Integer thumbid);

    /**
     * 分页查询
     *
     * @param thumbrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Thumbrecord> queryByPage(Thumbrecord thumbrecord, PageRequest pageRequest);

    Boolean isThumbRecordExist(Thumbrecord thumbrecord);

    /**
     * 新增数据
     *
     * @param thumbrecord 实例对象
     * @return 实例对象
     */
    Thumbrecord insert(Thumbrecord thumbrecord);

    /**
     * 修改数据
     *
     * @param thumbrecord 实例对象
     * @return 实例对象
     */
    Thumbrecord update(Thumbrecord thumbrecord);

    /**
     * 通过主键删除数据
     *
     * @param thumbid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer thumbid);

}
