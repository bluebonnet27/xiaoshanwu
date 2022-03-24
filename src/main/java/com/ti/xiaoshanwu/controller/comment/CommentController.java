package com.ti.xiaoshanwu.controller.comment;

import com.ti.xiaoshanwu.entity.Comment;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @RequestMapping("addcomment")
    @ResponseBody
    private String addNewComment(HttpSession session,
                                 Integer commentar,
                                 String content){
        Integer userid = (Integer) session.getAttribute("uid");
        JsonResult commentBackResult = new JsonResult();

        if(userid==null){
            commentBackResult.setResult(false);
            commentBackResult.setErrMsg("登录信息失效，请重新登录。");
        }else {
            Comment newComment = new Comment();
            Date now = new Date();
            newComment.setCommentarticleid(commentar);
            newComment.setCommentuserid(userid);
            newComment.setCommentcontent(content);
            newComment.setCommenttime(now);
            newComment.setCommentthumb(0);
            newComment.setCommenthot(0);
            newComment.setCommenttype(0);

            Comment submitComment = this.commentService.insert(newComment);

            commentBackResult.setResult(true);
            commentBackResult.setResMsg("评论成功,您的评论日期为 " + submitComment.getCommenttime());
        }

        return commentBackResult.toString();
    }
}
