package com.ti.xiaoshanwu.controller;

import com.sun.prism.PixelFormat;
import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.controller.tool.HotTool;
import com.ti.xiaoshanwu.entity.*;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.*;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/ind")
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    private ThemeService themeService;

    @Resource
    private ArticleService articleService;

    @Resource
    private BoardService boardService;

    /**
     * 跳转到用户首页.
     *
     * @param session  the session
     * @param model    the model
     * @return the string
     */
    @RequestMapping("userindex")
    public String goUserIndex(HttpSession session,
                              Model model){
        Integer userid = (Integer) session.getAttribute("uid");

        if(userid!=null){
            User user = this.userService.queryById(userid);

            //前端数据一次处理
            //筛选条件
            Article siftCondition = new Article();
            //排序依据
            Sort sort = Sort.by(Sort.Order.asc("articlepublishtime"));
            //分页请求
            PageRequest pageRequest = PageRequest.of(0, 5 ,sort);
            //执行
            Page<Article> articles = this.articleService.queryByPage1(siftCondition,pageRequest);

            //前端数据二次处理
            List<Article> articlesNew = articles.getContent();
            ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();

            for(Article article:articlesNew){
                ArticleImpl articleImpl2 = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl2);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);

            //最多帖子的主题
            Theme siftTheme = new Theme();
            List<Theme> themes = this.themeService.queryByNoPage(siftTheme);

            //去掉管理区
            themes.remove(0);

            themes.sort(Comparator.comparing(Theme::getThemecount));
            model.addAttribute("themes",themes);

            //查询最新公告内容
            Board siftBoard = new Board();
            List<Board> boards = this.boardService.queryByNoPage(siftBoard);
            model.addAttribute("board", this.boardService.convertToBoardImpl(boards.get(0)));

            return "index";
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    /**
     * 跳转到管理员首页.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @RequestMapping("/adminindex")
    public String goAdminIndex(HttpSession session,
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

                //用户数据可视化
                //sex 0 = male,1 = female,2 = spec
                User maleUser = new User();
                maleUser.setUsersex(0);
                User femaleUser = new User();
                femaleUser.setUsersex(1);
                User specUser = new User();
                specUser.setUsersex(2);

                Integer male = Math.toIntExact(this.userService.queryUserNum(maleUser));
                Integer female = Math.toIntExact(this.userService.queryUserNum(femaleUser));
                Integer specialSex = Math.toIntExact(this.userService.queryUserNum(specUser));

                model.addAttribute("malec",male);
                model.addAttribute("femalec",female);
                model.addAttribute("specc",specialSex);

                //帖子数据可视化
                Article articleProg = new Article();
                articleProg.setArticlethemeid(0);
                Article articleJob = new Article();
                articleJob.setArticlethemeid(1);
                Article articleAni = new Article();
                articleAni.setArticlethemeid(2);
                Article articleGam = new Article();
                articleGam.setArticlethemeid(3);
                Article articleLit = new Article();
                articleLit.setArticlethemeid(4);

                Integer articleProgCount =
                        Math.toIntExact(this.articleService.queryArticleNumByArticle(articleProg));
                Integer articleJobCount =
                        Math.toIntExact(this.articleService.queryArticleNumByArticle(articleJob));
                Integer articleAniCount =
                        Math.toIntExact(this.articleService.queryArticleNumByArticle(articleAni));
                Integer articleGamCount =
                        Math.toIntExact(this.articleService.queryArticleNumByArticle(articleGam));
                Integer articleLitCount =
                        Math.toIntExact(this.articleService.queryArticleNumByArticle(articleLit));

                model.addAttribute("arProg",articleProgCount);
                model.addAttribute("arJob",articleJobCount);
                model.addAttribute("arAni",articleAniCount);
                model.addAttribute("arGam",articleGamCount);
                model.addAttribute("arLit",articleLitCount);


                return "admin/adminindex";
            }
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    /**
     * 登出系统，销毁session并返回登录页
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @RequestMapping("logout")
    public String goLogout(HttpSession session,
                           Model model){
        session.removeAttribute("uid");
        //查询最新公告内容
        Board siftBoard = new Board();
        List<Board> boards = this.boardService.queryByNoPage(siftBoard);
        model.addAttribute("board", boards.get(0));
        return "login/loginpage";
    }
}
