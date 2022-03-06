package com.ti.xiaoshanwu.controller;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.service.AdminService;
import com.ti.xiaoshanwu.service.ArticleService;
import com.ti.xiaoshanwu.service.ThemeService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 首页跳转的测试方法.（已废弃）
     *
     * @param session  服务器内的session
     * @param username 用户名
     * @param model    model
     * @return index.html 或者 404
     */
    @RequestMapping("indextest")
    public String goIndex(HttpSession session,
                          String username,
                          Model model){
        if(session.getAttribute("uid")!=null){
            model.addAttribute("uname",username);
            return "index";
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

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
            Sort sort = Sort.by(Sort.Order.desc("articleid"));
            //分页请求
            PageRequest pageRequest = PageRequest.of(0, 5 ,sort);
            //执行
            Page<Article> articles = this.articleService.queryByPage1(siftCondition,pageRequest);

            //前端数据二次处理
            List<Article> articlesNew = articles.getContent();
            ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();

            HeadImgConverter headImgConverter = new HeadImgConverter();

            for(Article article:articlesNew){
                ArticleImpl articleImpl = new ArticleImpl();
                copyProperties(article,articleImpl);

                User targetUser = this.userService.queryById(article.getArticleauthorid());
                String articleAuthorNameImpl = targetUser.getUsername();
                String articleAuthorHeadImgUrlImpl =
                        headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());

                articleImpl.setArticleauthoridImpl(articleAuthorNameImpl);
                articleImpl.setArticleauthorImg(articleAuthorHeadImgUrlImpl);

                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);
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
        return "login/loginpage";
    }
}
