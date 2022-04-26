package com.ti.xiaoshanwu.controller.tool;

import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.Comment;
import com.ti.xiaoshanwu.service.CommentService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.Math;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 用于计算热度的类.
 *
 * @author TiHongsheng
 */
@Component
public class HotTool {

    @Resource
    private CommentService commentService;

    double gama = 0.1;

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date myDate;
    {
        try {
            myDate = dateFormat2.parse("2022-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double calculateArticleHot(Article article){
        //获取时间维度相关内容
        Comment siftCondition = new Comment();
        siftCondition.setCommentarticleid(article.getArticleid());

        Comment comment = this.commentService.getNewestComment(siftCondition);

        long deltaTime;
        if(comment!=null){
            deltaTime = (article.getArticlechangetime().getTime() -
                    article.getArticlepublishtime().getTime())/1000;
        }else {
            deltaTime = 0;
        }

        //获取互动量维度相关内容
        Integer replyNum = article.getArticlereplycount();
        Integer thumbNum = article.getArticlethumb();
        Integer collectNum = article.getArticlecollect();

        //获取内容维度相关内容
        double lgArticleContent = Math.log10(article.getArticlecontent().length());

        double articleHotBase = lgArticleContent + 1.0*replyNum + 1.75*thumbNum + 3.2*collectNum;
        double articleHotTop = Math.exp(-gama*((deltaTime*1.0)/86400));

        return articleHotBase*articleHotTop;
    }

    public double calculateCommentHot(Comment comment){
        //获取时间维度相关内容
        long deltaTime;
        deltaTime = (comment.getCommenttime().getTime()-myDate.getTime())/1000;

        //获取互动量维度相关内容
        Integer thumbNum = comment.getCommentthumb();

        //获取内容维度相关内容
        double lgCommentContent = Math.log10(comment.getCommentcontent().length());

        //计算
        double commentHotBase = lgCommentContent + 1.75*thumbNum;
        double commentHotTop = Math.exp(-gama*((deltaTime*1.0)/86400));

        return commentHotBase*commentHotTop;
    }
}
