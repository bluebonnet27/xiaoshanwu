package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Comment;
import com.ti.xiaoshanwu.dao.CommentDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.CommentImpl;
import com.ti.xiaoshanwu.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-03-24 11:45:37
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentid 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(Integer commentid) {
        return this.commentDao.queryById(commentid);
    }

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Comment> queryByPage(Comment comment, PageRequest pageRequest) {
        long total = this.commentDao.count(comment);
        return new PageImpl<>(this.commentDao.queryAllByLimit(comment, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.queryById(comment.getCommentid());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentid) {
        return this.commentDao.deleteById(commentid) > 0;
    }

    @Override
    public CommentImpl convertToCommentImpl(Comment comment) {
        CommentImpl commentImpl = new CommentImpl();
        copyProperties(comment,commentImpl);

        HeadImgConverter headImgConverter = new HeadImgConverter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        User commentUser = this.userDao.queryById(comment.getCommentuserid());
        commentImpl.setCommentUserNameImpl(commentUser.getUsername());

        String commentDate = dateFormat.format(comment.getCommenttime());
        commentImpl.setCommentDateImpl(commentDate);

        String bgimg =
                headImgConverter.imgConvert(commentUser.getUserimg()==null?0:commentUser.getUserimg());
        commentImpl.setCommentUserHeadImpl(bgimg);

        return commentImpl;
    }
}
