package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Folder;
import com.ti.xiaoshanwu.dao.FolderDao;
import com.ti.xiaoshanwu.service.FolderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Folder)表服务实现类
 *
 * @author makejava
 * @since 2022-04-08 10:23:14
 */
@Service("folderService")
public class FolderServiceImpl implements FolderService {
    @Resource
    private FolderDao folderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param folderid 主键
     * @return 实例对象
     */
    @Override
    public Folder queryById(Integer folderid) {
        return this.folderDao.queryById(folderid);
    }

    /**
     * 分页查询
     *
     * @param folder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Folder> queryByPage(Folder folder, PageRequest pageRequest) {
        long total = this.folderDao.count(folder);
        return new PageImpl<>(this.folderDao.queryAllByLimit(folder, pageRequest), pageRequest, total);
    }

    /**
     * 不分页查询
     *
     * @param folder the folder
     * @return the list
     */
    @Override
    public List<Folder> queryByPage(Folder folder) {
        return this.folderDao.queryAllByLimitNoPage(folder);
    }

    /**
     * 新增数据
     *
     * @param folder 实例对象
     * @return 实例对象
     */
    @Override
    public Folder insert(Folder folder) {
        this.folderDao.insert(folder);
        return folder;
    }

    /**
     * 修改数据
     *
     * @param folder 实例对象
     * @return 实例对象
     */
    @Override
    public Folder update(Folder folder) {
        this.folderDao.update(folder);
        return this.queryById(folder.getFolderid());
    }

    /**
     * 通过主键删除数据
     *
     * @param folderid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer folderid) {
        return this.folderDao.deleteById(folderid) > 0;
    }
}
