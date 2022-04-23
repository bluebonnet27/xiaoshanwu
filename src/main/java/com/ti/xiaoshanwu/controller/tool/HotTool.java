package com.ti.xiaoshanwu.controller.tool;

import com.ti.xiaoshanwu.entity.Article;
import java.lang.Math;

/**
 * 用于计算热度的类.
 *
 * @author TiHongsheng
 */
public class HotTool {

    double gama = 0.1;

    public double calculateArticleHot(Article article){
        //获取时间维度相关内容
        long deltaTime = (article.getArticlechangetime().getTime() -
                article.getArticlepublishtime().getTime())/1000;

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
}
