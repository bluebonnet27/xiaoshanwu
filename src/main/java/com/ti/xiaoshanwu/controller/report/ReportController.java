package com.ti.xiaoshanwu.controller.report;

import com.ti.xiaoshanwu.controller.tool.HotTool;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.Comment;
import com.ti.xiaoshanwu.entity.Report;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.ArticleService;
import com.ti.xiaoshanwu.service.CommentService;
import com.ti.xiaoshanwu.service.ReportService;
import com.ti.xiaoshanwu.service.UserService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("rep")
public class ReportController {

    @Resource
    private ReportService reportService;

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    @Autowired
    private HotTool hotTool;

    /**
     * 提交举报的方法.
     *
     * @param session      the session
     * @param reporttype   the reporttype
     * @param reportreason the reportreason
     * @param reporttoid   the reporttoid
     * @return 适用于 article/articlemain.html
     */
    @RequestMapping("addreport")
    @ResponseBody
    public String submitReport(HttpSession session,
                               @RequestParam Integer reporttype,
                               @RequestParam String reportreason,
                               @RequestParam Integer reporttoid
                               ){
        JsonResult<?> reportBackResult = new JsonResult<>();
        //insert into report(reporttype, reporttime, reportreason, reportuserid, reporttoid, reportstate)
        //reporttype =0 is article, =1 is comment , =2 is driftbottle
        //time
        Date reportTime = new Date();
        Integer userId = (int) session.getAttribute("uid");
        User reportUser = this.userService.queryById(userId);

        if(reportUser == null){
            reportBackResult.setResult(false);
            reportBackResult.setErrMsg("用户信息已失效，请重新登录");
            return reportBackResult.toString();
        }else {
            Report insertReport = new Report();

            insertReport.setReporttype(reporttype);
            insertReport.setReportuserid(userId);
            insertReport.setReporttoid(reporttoid);
            insertReport.setReportstate(0);

            if(this.reportService.isReportExist(insertReport)){
                reportBackResult.setResult(false);
                reportBackResult.setErrMsg("您有一条正在进行的举报等待管理员处理，请不要重复举报");
                return reportBackResult.toString();
            }

            insertReport.setReporttime(reportTime);
            insertReport.setReportreason(reportreason);

            Report backResultReport = this.reportService.insert(insertReport);

            if(backResultReport == null){
                reportBackResult.setResult(false);
                reportBackResult.setErrMsg("由于数据库的原因举报失败，请联系管理员");
            }else {
                //暂时清空热度值
                if(reporttype == 0){
                    Article reportAricle = this.articleService.queryById(reporttoid);
                    reportAricle.setArticlehot(-1);
                    this.articleService.update(reportAricle);
                }
                reportBackResult.setResult(true);
                reportBackResult.setResMsg("举报成功，以下是您的举报信息" + backResultReport.getReportreason());
            }
            return reportBackResult.toString();
        }
    }

    @RequestMapping("disagreport")
    @ResponseBody
    public String disagreeReport(HttpSession session,Integer reportid){
        JsonResult<?> reportBackResult = new JsonResult<>();
        Report targetReport = this.reportService.queryById(reportid);

        //更改举报状态 0 = 待处理,2 = 已驳回
        targetReport.setReportstate(2);
        this.reportService.update(targetReport);

        //恢复帖子/评论热度
        if(targetReport.getReporttype() == 0){
            Article targetArticle = this.articleService.queryById(targetReport.getReporttoid());
            Integer newHot = this.hotTool.intCalculateHot(this.hotTool.calculateArticleHot(targetArticle));
            targetArticle.setArticlehot(newHot);
            this.articleService.update(targetArticle);
        }else{
            Comment targetComment = this.commentService.queryById(targetReport.getReporttoid());
            Integer newHot = this.hotTool.intCalculateHot(this.hotTool.calculateCommentHot(targetComment));
            targetComment.setCommenthot(newHot);
            this.commentService.update(targetComment);
        }

        reportBackResult.setResult(true);
        reportBackResult.setResMsg("驳回举报成功，帖子/评论热度已恢复原样");

        return reportBackResult.toString();
    }
}
