package com.ti.xiaoshanwu.controller.admin;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ti.xiaoshanwu.controller.tool.HotTool;
import com.ti.xiaoshanwu.entity.*;
import com.ti.xiaoshanwu.entity.impl.*;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/adm")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private ThemeService themeService;

    @Resource
    private BoardService boardService;

    @Resource
    private LoginrecordService loginrecordService;

    @Resource
    private ArticleService articleService;

    @Autowired
    private HotTool hotTool;

    @Resource
    private CommentService commentService;

    @Resource
    private ReportService reportService;

    @RequestMapping("tochangepwd")
    public String toChangePwd(HttpSession session,
                              Model model){
        if(session.getAttribute("uid")!=null){
            int adminid = (int) session.getAttribute("uid");
            Admin foundAdmin = this.adminService.queryById(adminid);
            if(foundAdmin==null){
                model.addAttribute("msg","IndexController - goAdminIndex - foundAdmin is null");
                return "messagepage";
            }else {
                String adminname = foundAdmin.getAdminname();
                model.addAttribute("adminname",adminname);

                return "admin/adminchangepwd";
            }
        }else {
            model.addAttribute("msg","session???");
            return "messagepage";
        }
    }

    @RequestMapping("changepwd")
    public @ResponseBody String changePwd(HttpSession session,
                                          String adminpwd){
        JsonResult changePwdBackResult = new JsonResult();

        if(session.getAttribute("uid")!=null){
            int adminid = (int) session.getAttribute("uid");
            Admin foundAdmin = this.adminService.queryById(adminid);


            if(foundAdmin==null){
                changePwdBackResult.setResult(false);
                changePwdBackResult.setErrMsg("????????????????????????????????????!");

                session.removeAttribute("uid");
            }else {
                if(adminpwd==null){
                    changePwdBackResult.setResult(false);
                    changePwdBackResult.setResMsg("??????????????????");
                }else {
                    Admin updateAdmin = new Admin(adminid,adminpwd);

                    Admin newAdmin = adminService.update(updateAdmin);

                    changePwdBackResult.setResult(true);
                    changePwdBackResult.setResMsg("????????????????????????" + newAdmin.getAdminname() + "\n" +
                            "????????????????????????????????????????????????");

                    session.removeAttribute("uid");
                }
            }
        }else {
            changePwdBackResult.setResult(false);
            changePwdBackResult.setErrMsg("????????????????????????????????????!");
        }

        return changePwdBackResult.toString();
    }

    @RequestMapping("allusersdefault")
    public String getAllUsers(HttpSession session,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //?????????????????????
        page = page -1;
        //????????????
        User siftCondition = new User();
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("userid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //??????
        Page<User> userPages = this.userService.queryByPage(siftCondition,pageRequest);
        ArrayList<UserImpl> userImpls = new ArrayList<>();

        //??????????????????????????????
        List<User> users = userPages.getContent();

        for(User user:users){
            UserImpl userImpl = this.userService.convertUserToUserImpl(user);
            userImpls.add(userImpl);
        }
        model.addAttribute("userpages",userPages);
        model.addAttribute("userImpls",userImpls);

        return "admin/uuser/adminusermng";
    }

    @RequestMapping("allusershead")
    public String getAllUsersHead(HttpSession session,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //?????????????????????
        page = page -1;
        //????????????
        User siftCondition = new User(1);
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("userid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //??????
        Page<User> userPages = this.userService.queryByPage(siftCondition,pageRequest);
        ArrayList<UserImpl> userImpls = new ArrayList<>();

        //??????????????????????????????
        List<User> users = userPages.getContent();

        for(User user:users){
            UserImpl userImpl = this.userService.convertUserToUserImpl(user);
            userImpls.add(userImpl);
        }
        model.addAttribute("userpages",userPages);
        model.addAttribute("userImpls",userImpls);

        return "admin/uuser/adminusermng2";
    }

    @RequestMapping("allthemes")
    public String getAllThemes(HttpSession session,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //?????????????????????
        page = page -1;
        //????????????
        Theme siftCondition = new Theme();
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("themeid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //??????
        Page<Theme> themePages = this.themeService.queryByPage(siftCondition,pageRequest);
        ArrayList<ThemeImpl> themeImpls = new ArrayList<>();

        //??????????????????????????????
        List<Theme> themes = themePages.getContent();

        for(Theme theme:themes){
            ThemeImpl themeImpl = this.themeService.convertToThemeImpl(theme);
            themeImpls.add(themeImpl);
        }

        model.addAttribute("themepages",themePages);
        model.addAttribute("themeImpls",themeImpls);
        return "admin/ttheme/adminthememng";
    }

    @RequestMapping("allreports")
    public String getAllReports(HttpSession session,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer limit,
                                Model model,
                                @RequestParam(defaultValue = "-1") Integer reporttype){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //?????????????????????
        page = page -1;
        //????????????
        Report reportCondition = new Report();
        if(reporttype != -1){
            reportCondition.setReporttype(reporttype);
        }
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("reportid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //??????
        Page<Report> reportPages = this.reportService.queryByPage(reportCondition,pageRequest);
        ArrayList<ReportImpl> reportImpls = new ArrayList<>();

        //??????????????????????????????
        List<Report> reports = reportPages.getContent();

        for(Report report:reports){
            ReportImpl reportImpl = this.reportService.convertToImpl(report);
            reportImpls.add(reportImpl);
        }

        model.addAttribute("reportpages",reportPages);
        model.addAttribute("reportImpls",reportImpls);

        model.addAttribute("reporttype",reporttype);
        return "admin/report/adminreports";
    }

    @RequestMapping("userdata")
    public String getUserData(HttpSession session,
                              Model model){

        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //??????
        User man = new User();
        man.setUsersex(0);
        User woman = new User();
        woman.setUsersex(1);
        User thirdman = new User();
        thirdman.setUsersex(2);

        long manNum = this.userService.queryUserNum(man);
        long womanNum = this.userService.queryUserNum(woman);
        long thirdmanNum = this.userService.queryUserNum(thirdman);

        model.addAttribute("totaluser",manNum + womanNum + thirdmanNum);
        model.addAttribute("mannum",manNum);
        model.addAttribute("womannum",womanNum);
        model.addAttribute("thirdmannum",thirdmanNum);

        //
        return "admin/uuser/adminusermngdata";
    }

    @RequestMapping("alladminboards")
    public String selectAllAdminBoards(Model model,
                                       HttpSession session,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "5") Integer limit){
        if(session.getAttribute("uid")==null){
            model.addAttribute("errtitle","session???");
            model.addAttribute("errorsubtitle","???");
            model.addAttribute("errtext","com/ti/xiaoshanwu/controller/admin/AdminController.java");
            return "errorhandle";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //?????????????????????
        page = page -1;
        //????????????
        Board siftCondition = new Board();
        siftCondition.setBoardtype(0);
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("boardid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //??????
        Page<Board> boardPages = this.boardService.queryByPage(siftCondition,pageRequest);

        ArrayList<BoardImpl> boardImpls = new ArrayList<>();

        //??????????????????????????????
        List<Board> boards = boardPages.getContent();

        for(Board board:boards){
            BoardImpl boardImpl = this.boardService.convertToBoardImpl(board);
            boardImpls.add(boardImpl);
        }

        model.addAttribute("boardpages",boardPages);
        model.addAttribute("boardImpls",boardImpls);
        return "admin/board/adminboard";
    }

    @RequestMapping("toaddnewboard")
    public String toAddNewBoard(Model model,HttpSession session){
        if(session.getAttribute("uid")==null){
            model.addAttribute("errtitle","session???");
            model.addAttribute("errorsubtitle","???");
            model.addAttribute("errtext","com/ti/xiaoshanwu/controller/admin/AdminController.java");
            return "errorhandle";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);
        return "admin/board/adminnewboard";
    }

    @RequestMapping("newboard")
    public String addNewBoard(Model model,
                              HttpSession session,
                              String botitle,
                              String bocontent){
        if(session.getAttribute("uid")==null){
            model.addAttribute("errtitle","session???");
            model.addAttribute("errorsubtitle","???");
            model.addAttribute("errtext","com/ti/xiaoshanwu/controller/admin/AdminController.java");
            return "errorhandle";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        Board addBoard = new Board();
        //???????????????????????????
        addBoard.setBoardtype(0);
        //??????
        Date now = new Date();
        addBoard.setBoarddate(now);
        //??????????????????????????????
        addBoard.setBoardtitle(botitle);
        addBoard.setBoardcontent(bocontent);
        //??????????????????????????????
        addBoard.setBoardthumb(0);
        addBoard.setBoardagainst(0);
        addBoard.setBoardtheme(-1);

        Board backBoard = this.boardService.insert(addBoard);

        model.addAttribute("backboard",backBoard);
        return "admin/board/adminnewboardsuccess";
    }

    @RequestMapping("touserdetail")
    public String toUserDetail(Model model,
                               HttpSession session,
                               Integer userid){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //????????????????????????
        User targetUser = this.userService.queryById(userid);
        UserImpl targetUserImpl = this.userService.convertUserToUserImpl(targetUser);
        model.addAttribute("user",targetUserImpl);

        return "admin/uuser/userdetail";
    }

    @RequestMapping("toreportdetail")
    public String toReportDetail(Model model,
                                 HttpSession session,
                                 Integer reportid,
                                 Integer reporttype){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //????????????????????????
        Report foundReport = this.reportService.queryById(reportid);
        ReportImpl reportImpl = this.reportService.convertToImpl(foundReport);

        model.addAttribute("reporttype",reporttype);
        if(reporttype == 0){
            Article article = this.articleService.queryById(foundReport.getReporttoid());
            model.addAttribute("ctt",article);
        }else if(reporttype == 1){
            Comment comment = this.commentService.queryById(foundReport.getReporttoid());
            Article article = new Article();
            article.setArticlecontent(comment.getCommentcontent());
            model.addAttribute("ctt",article);
        }else {
            Article article = this.articleService.queryById(foundReport.getReporttoid());
            model.addAttribute("ctt",article);
        }


        model.addAttribute("report",reportImpl);

        return "admin/report/reportdetail";
    }

    @RequestMapping("touserdetailmng")
    public String toUserDetailMng(Model model,
                                  HttpSession session,
                                  Integer userid){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //????????????????????????
        User targetUser = this.userService.queryById(userid);
        UserImpl targetUserImpl = this.userService.convertUserToUserImpl(targetUser);
        model.addAttribute("user",targetUserImpl);

        return "admin/uuser/userdetailcon";
    }

    @RequestMapping("tologinrecords")
    public String toLoginRecords(Model model,
                                 HttpSession session,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "5") Integer limit,
                                 @RequestParam(defaultValue = "-1") Integer logintype){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //????????????????????????
        //?????????????????????
        page = page -1;
        Loginrecord siftCondition = new Loginrecord();
        if(logintype != -1){
            siftCondition.setLogintype(logintype);
        }
        model.addAttribute("logintype",logintype);
        //????????????
        Sort sort = Sort.by(Sort.Order.desc("userid"));
        //????????????
        PageRequest pageRequest = PageRequest.of(page, limit, sort);

        Page<Loginrecord> pages = this.loginrecordService.queryByPage(siftCondition,pageRequest);
        List<Loginrecord> loginrecords = pages.getContent();
        ArrayList<LoginrecordImpl> loginrecordImpls = new ArrayList<>();

        for (Loginrecord loginrecord:loginrecords){
            LoginrecordImpl loginrecord1 = this.loginrecordService.convertToLoginrecordImpl(loginrecord);
            loginrecordImpls.add(loginrecord1);
        }

        model.addAttribute("pages",pages);
        model.addAttribute("loginrecordimpls",loginrecordImpls);

        return "admin/record/adminloginrecords";
    }

    @RequestMapping("articlesmng")
    public String toArticlesMng(Model model,HttpSession session){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);
        return "admin/article/adminarticlesmng";
    }

    @RequestMapping("refreshhot")
    public String refreshAllHot(Model model,HttpSession session){
        Article siftCondition = new Article();
        List<Article> articles = this.articleService.queryArticles(siftCondition);
        Integer count = 0;

        for (Article article:articles){
            Integer articleHot = (int) Math.round(this.hotTool.calculateArticleHot(article) * 100000);
            article.setArticlehot(articleHot);
            Article articleBack = this.articleService.update(article);
            count++;
        }

        Comment commentSiftCondition = new Comment();
        List<Comment> comments = this.commentService.queryByPage(commentSiftCondition);
        Integer commentCount = 0;
        for (Comment comment:comments){
            Integer commentHot = (int) Math.round(this.hotTool.calculateCommentHot(comment) * 100000);
            comment.setCommenthot(commentHot);
            Comment commentBack = this.commentService.update(comment);
            commentCount++;
        }

        model.addAttribute("errortitle","?????????????????????");
        model.addAttribute("errsubtitle","?????????"+count+"?????????,"+commentCount+"?????????");
        model.addAttribute("errtext","com.ti.xiaoshanwu.controller.admin.AdminController.refreshAllHot");

        return "errorhandle";
    }

    @RequestMapping("mnghot")
    public String hotMng(Model model,HttpSession session){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session???");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);
        return "admin/hot/hotmng";
    }
}
