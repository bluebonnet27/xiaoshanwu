package com.ti.xiaoshanwu.controller.report;

import com.ti.xiaoshanwu.entity.Report;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.ReportService;
import com.ti.xiaoshanwu.service.UserService;
import org.mockito.Mock;
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
                reportBackResult.setResult(true);
                reportBackResult.setResMsg("举报成功，以下是您的举报信息" + backResultReport.getReportreason());
            }
            return reportBackResult.toString();
        }
    }
}
