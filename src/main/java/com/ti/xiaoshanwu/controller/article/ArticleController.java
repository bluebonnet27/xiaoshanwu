package com.ti.xiaoshanwu.controller.article;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.ArticleService;
import com.ti.xiaoshanwu.service.ThemeService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private ThemeService themeService;

    @RequestMapping("defaultnew")
    public String showArticlesDefaultAndDefault(Model model,
                                                @RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer limit,
                                                HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");

        if(userid!=null){
            User user = this.userService.queryById(userid);

            //程序页转为类页
            page = page -1;
            //筛选条件
            Article siftCondition = new Article();
            //排序依据
            Sort sort = Sort.by(Sort.Order.desc("articlepublishtime"));
            //分页请求
            PageRequest pageRequest = PageRequest.of(page, limit, sort);
            //执行
            Page<Article> articles = this.articleService.queryByPageNew(siftCondition,pageRequest);

            //前端数据二次处理
            List<Article> articlesNew = articles.getContent();
            ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();


            for(Article article:articlesNew){
                ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);
            return "mainpage/defaultandnew";
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("defaultindex")
    public String showArticlesDefaultForIndex(Model model,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "5") Integer limit,
                                              HttpSession session){
        //程序页转为类页
        page = page -1;
        //筛选条件
        Article siftCondition = new Article();
        //排序依据
        Sort sort = Sort.by(Sort.Order.desc("articleid"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //执行
        Page<Article> articles = this.articleService.queryByPage1(siftCondition,pageRequest);

        //前端数据二次处理
        List<Article> articlesNew = articles.getContent();

        ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();


        for(Article article:articlesNew){
            ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
            articlesImpl.add(articleImpl);
        }

        Integer userid = (Integer) session.getAttribute("uid");

        if(userid!=null){
            User user = this.userService.queryById(userid);

            model.addAttribute("user",user);
        }
        model.addAttribute("pages",articles);
        model.addAttribute("articlesimpl",articlesImpl);
        return "index";
    }

    @RequestMapping ("articlemore")
    public String showArticleMore(Model model,Integer articleid,HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid==null){
            model.addAttribute("username","请登录");
        }else {
            User user = this.userService.queryById(userid);
            model.addAttribute("username",user.getUsername());
        }

        Article article = this.articleService.queryById(articleid);

        User author = this.userService.queryById(article.getArticleauthorid());
        UserImpl userImpl = this.userService.convertUserToUserImpl(author);
        model.addAttribute("userimpl",userImpl);

        ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);

        model.addAttribute("article",articleImpl);

        return "article/articlemain";
    }

    @RequestMapping("toarticleup")
    public String touploadArticle(Model model,
                                  HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid==null){
            model.addAttribute("username","请登录");
        }else {
            User user = this.userService.queryById(userid);
            model.addAttribute("username",user.getUsername());
        }


        return "article/articleupload";
    }

    /**
     * 向后端发送文章的方法.
     *
     * @param model          the model
     * @param session        the session
     * @param articlethemeid the articlethemeid
     * @param articletitle   the articletitle
     * @param articlecontent the articlecontent
     * @return json结果
     */
    @RequestMapping("insertarticle")
    public String uploadArticle(Model model,
                                HttpSession session,
                                Integer articlethemeid,
                                String articletitle,
                                String articlecontent){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid==null){
            model.addAttribute("errtitle","登录信息已失效，请重新登录！");
            model.addAttribute("errsubtitle","session为空");
            model.addAttribute("errtext","src/main/java/com/ti/xiaoshanwu/controller/article" +
                    "/ArticleController.java");

            return "errorhandle";
        }else if(Objects.equals(articletitle, " ")){
            model.addAttribute("errtitle","标题为空");
            model.addAttribute("errsubtitle","标题为空");
            model.addAttribute("errtext","src/main/java/com/ti/xiaoshanwu/controller/article" +
                    "/ArticleController.java");

            return "errorhandle";
        }else if(Objects.equals(articlecontent, " ")){
            model.addAttribute("errtitle","正文为空");
            model.addAttribute("errsubtitle","正文为空");
            model.addAttribute("errtext","src/main/java/com/ti/xiaoshanwu/controller/article" +
                    "/ArticleController.java");

            return "errorhandle";
        }else {
            Article insertArticle = new Article();

            Date currentTime = new Date();

            insertArticle.setArticlethemeid(articlethemeid);
            insertArticle.setArticleauthorid(userid);
            insertArticle.setArticlereplycount(0);

            insertArticle.setArticletitle(articletitle);
            insertArticle.setArticlecontent(articlecontent);

            insertArticle.setArticlepublishtime(currentTime);
            insertArticle.setArticlechangetime(currentTime);

            insertArticle.setArticlethumb(0);
            insertArticle.setArticlecollect(0);

            insertArticle.setArticlehot(0);

            Article backArticle = this.articleService.insert(insertArticle);

            if(backArticle==null){
                model.addAttribute("errtitle","数据库错误");
                model.addAttribute("errsubtitle","由于数据库的原因，更新失败");
                model.addAttribute("errtext","src/main/java/com/ti/xiaoshanwu/controller/article" +
                        "/ArticleController.java");

                return "errorhandle";
            }else {
                model.addAttribute("errtitle","新建文章成功");
                model.addAttribute("errsubtitle","您的文章id是"+backArticle.getArticleid());
                model.addAttribute("errtext","src/main/java/com/ti/xiaoshanwu/controller/article" +
                        "/ArticleController.java");

                return "errorhandle";
            }
        }
    }
}
