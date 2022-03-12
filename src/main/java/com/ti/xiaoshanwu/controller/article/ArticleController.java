package com.ti.xiaoshanwu.controller.article;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.service.ArticleService;
import com.ti.xiaoshanwu.service.ThemeService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
                String themeName = this.themeService.queryById(article.getArticlethemeid()).getThemename();
                String bgimg =
                        headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());

                articleImpl.setArticleauthoridImpl(articleAuthorNameImpl);
                articleImpl.setArticleauthorImg(articleAuthorHeadImgUrlImpl);
                articleImpl.setThemeName(themeName);
                articleImpl.setArticleImgUrl(bgimg);

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
        Sort sort = Sort.by(Sort.Order.desc("articlepublishtime"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
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
            String themeName = this.themeService.queryById(article.getArticlethemeid()).getThemename();
            String bgimg =
                    headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());

            articleImpl.setArticleauthoridImpl(articleAuthorNameImpl);
            articleImpl.setArticleauthorImg(articleAuthorHeadImgUrlImpl);
            articleImpl.setThemeName(themeName);
            articleImpl.setArticleImgUrl(bgimg);

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

    @RequestMapping("articlemore")
    public String showArticleMore(Model model,Integer articleid,HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            model.addAttribute("username","请登录");
        }else {
            User user = this.userService.queryById(userid);
            model.addAttribute("username",user.getUsername());
        }

        Article article = this.articleService.queryById(articleid);
        model.addAttribute("article",article);

        return "article/articlemain";
    }
}
