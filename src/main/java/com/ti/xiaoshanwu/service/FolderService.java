package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Folder)表服务接口
 *
 * @author makejava
 * @since 2022-04-08 10:23:14
 */
public interface FolderService {

    /**
     * 通过ID查询单条数据
     *
     * @param folderid 主键
     * @return 实例对象
     */
    Folder queryById(Integer folderid);

    /**
     * 分页查询
     *
     * @param folder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Folder> queryByPage(Folder folder, PageRequest pageRequest);

    /**
     * 不分页查询
     *
     * @param folder the folder
     * @return the list
     */
    List<Folder> queryByPage(Folder folder);

    /**
     * 新增数据
     *
     * @param folder 实例对象
     * @return 实例对象
     */
    Folder insert(Folder folder);

    /**
     * 修改数据
     *
     * @param folder 实例对象
     * @return 实例对象
     */
    Folder update(Folder folder);

    /**
     * 通过主键删除数据
     *
     * @param folderid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer folderid);

}
