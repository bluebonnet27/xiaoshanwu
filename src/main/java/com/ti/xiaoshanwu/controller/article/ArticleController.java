package com.ti.xiaoshanwu.controller.article;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.controller.tool.HotTool;
import com.ti.xiaoshanwu.entity.*;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.entity.impl.CommentImpl;
import com.ti.xiaoshanwu.entity.impl.ThemeImpl;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;

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

    @Resource
    private ThumbrecordService thumbrecordService;

    @Resource
    private CommentService commentService;

    @Resource
    private CollectService collectService;

    @Resource
    private FolderService folderService;

    @Resource
    private BoardService boardService;

    @Autowired
    private HotTool hotTool;

    //主题四种排序方式
    static final int ORDER_DEFAULT= 0;
    static final int ORDER_HOT= 1;
    static final int ORDER_NEW= 2;
    static final int ORDER_NO_REPLY= 3;


    @RequestMapping("totheme")
    public String showArticleToTheme(Model model,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  @RequestParam(defaultValue = "0") Integer ord,
                                  @RequestParam(defaultValue = "0") Integer tid,
                                  HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");

        if(userid!=null){
            User user = this.userService.queryById(userid);

            //程序页转为类页
            page = page -1;

            //0=default,1=hot,2=new,3=no reply
            //筛选条件
            Article siftCondition = new Article();

            siftCondition.setArticlethemeid(tid);
            model.addAttribute("tid",tid);

            if(ord==ORDER_NO_REPLY){
                siftCondition.setArticlereplycount(0);
            }

            //排序依据，已经失效，使用数据库排序代替
            Sort sort = Sort.by(Sort.Order.desc("articleid"));

            //分页请求
            PageRequest pageRequest = PageRequest.of(page, limit, sort);

            //执行
            Page<Article> articles;

            if(ord==ORDER_DEFAULT){
                articles = this.articleService.queryByPage1(siftCondition,pageRequest);
            }else if(ord==ORDER_HOT){
                articles = this.articleService.queryByPageHot(siftCondition,pageRequest);
            }else {
                articles = this.articleService.queryByPageNew(siftCondition,pageRequest);
            }

            //前端数据二次处理
            List<Article> articlesNew = articles.getContent();
            ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();


            for(Article article:articlesNew){
                //去除热度为-1的帖子
                if(article.getArticlehot() < 0){
                    continue;
                }
                ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);
            model.addAttribute("ord",ord);

            Theme progTheme = this.themeService.queryById(tid);
            
            ThemeImpl themeImpl = this.themeService.convertToThemeImpl(progTheme);
            model.addAttribute("theme",themeImpl);

            User themeAdmin = this.userService.queryById(progTheme.getThemeadminid());
            UserImpl userImpl = this.userService.convertUserToUserImpl(themeAdmin);
            model.addAttribute("themeadmin",userImpl);

            return "mainpage/themeindex";
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("defaulthot")
    public String showArticlesDefaultAndHot(Model model,
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
            Sort sort = Sort.by(Sort.Order.desc("articlehot"));
            //分页请求
            PageRequest pageRequest = PageRequest.of(page, limit, sort);
            //执行
            Page<Article> articles = this.articleService.queryByPageHot(siftCondition,pageRequest);

            //前端数据二次处理
            List<Article> articlesNew = articles.getContent();
            ArrayList<ArticleImpl> articlesImpl = new ArrayList<>();


            for(Article article:articlesNew){
                //去除热度为-1的帖子
                if(article.getArticlehot() < 0){
                    continue;
                }
                ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);

            //查询最新公告内容
            Board siftBoard = new Board();
            List<Board> boards = this.boardService.queryByNoPage(siftBoard);
            model.addAttribute("board", this.boardService.convertToBoardImpl(boards.get(0)));

            return "mainpage/defaultandhot";
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("defaultnore")
    public String showArticlesDefaultAndNoReply(Model model,
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
            siftCondition.setArticlereplycount(0);
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
                //去除热度为-1的帖子
                if(article.getArticlehot() < 0){
                    continue;
                }
                ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);

            //查询最新公告内容
            Board siftBoard = new Board();
            List<Board> boards = this.boardService.queryByNoPage(siftBoard);
            model.addAttribute("board", this.boardService.convertToBoardImpl(boards.get(0)));

            return "mainpage/defaultandzero";
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

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
                //去除热度为-1的帖子
                if(article.getArticlehot() < 0){
                    continue;
                }
                ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);
                articlesImpl.add(articleImpl);
            }

            model.addAttribute("user",user);
            model.addAttribute("pages",articles);
            model.addAttribute("articlesimpl",articlesImpl);

            //查询最新公告内容
            Board siftBoard = new Board();
            List<Board> boards = this.boardService.queryByNoPage(siftBoard);
            model.addAttribute("board", this.boardService.convertToBoardImpl(boards.get(0)));

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

        //最多帖子的主题
        Theme siftTheme = new Theme();
        List<Theme> themes = this.themeService.queryByNoPage(siftTheme);

        //去掉管理区
        themes.remove(0);

        //查询最新公告内容
        Board siftBoard = new Board();
        List<Board> boards = this.boardService.queryByNoPage(siftBoard);
        model.addAttribute("board", this.boardService.convertToBoardImpl(boards.get(0)));

        themes.sort(Comparator.comparing(Theme::getThemecount));
        model.addAttribute("themes",themes);
        return "index";
    }

    @RequestMapping ("articlemore")
    public String showArticleMore(Model model,
                                  Integer articleid,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  @RequestParam(defaultValue = "0") Integer corder,
                                  HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid==null){
            model.addAttribute("username","请登录");
        }else {
            User user = this.userService.queryById(userid);
            UserImpl userImpl = this.userService.convertUserToUserImpl(user);
            model.addAttribute("username",user.getUsername());
            model.addAttribute("userimpl",userImpl);
        }

        Article article = this.articleService.queryById(articleid);
        Date now = new Date();

        User author = this.userService.queryById(article.getArticleauthorid());
        UserImpl userImpl = this.userService.convertUserToUserImpl(author);
        model.addAttribute("userimpl",userImpl);

        ArticleImpl articleImpl = this.articleService.convertToArticleImpl(article);

        model.addAttribute("article",articleImpl);

        //加载收藏
        Collect siftCollect = new Collect();
        siftCollect.setArticleid(articleid);
        siftCollect.setUserid(userid);

        List<Collect> collects = this.collectService.queryByNoPage(siftCollect);
        if(collects.isEmpty()){
            Collect fakeCollect = new Collect(1,1,1,now,1);
            collects.add(fakeCollect);

            model.addAttribute("iscollectempty",true);
            model.addAttribute("collects",collects);
        }else {
            model.addAttribute("iscollectempty",false);
            model.addAttribute("collects",collects);
        }

        //加载用户所有收藏夹备用
        Folder folderSift = new Folder();
        folderSift.setFolderuser(userid);
        List<Folder> folders = this.folderService.queryByPage(folderSift);
        model.addAttribute("folders",folders);

        //加载评论
        //程序页转为类页
        page = page -1;
        //筛选条件
        Comment siftComment = new Comment();
        siftComment.setCommentarticleid(articleid);

        //0 = default
        //1 = new
        //2 = hot
        //3 = thumb
        model.addAttribute("corder",corder);


        //排序依据，已经失效，使用数据库排序代替
        Sort sort = Sort.by(Sort.Order.desc("commentid"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //执行
        Page<Comment> comments;

        //分类
        comments = this.commentService.queryByPage(siftComment,pageRequest,corder);

        List<Comment> commentsReal = comments.getContent();
        ArrayList<CommentImpl> commentsImpl = new ArrayList<>();;

        for (Comment comment:commentsReal){
            CommentImpl addCommentImpl = this.commentService.convertToCommentImpl(comment);
            commentsImpl.add(addCommentImpl);
        }

        model.addAttribute("pages",comments);
        model.addAttribute("commentsImpl",commentsImpl);

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

    @RequestMapping("thumb")
    @ResponseBody
    public String thumbUpArticle(HttpSession session,
                                 Integer articleid){
        Integer userid = (Integer) session.getAttribute("uid");
        JsonResult backResult = new JsonResult();
        Thumbrecord thumbrecord = new Thumbrecord();
        //用户点赞
        thumbrecord.setThumbbytype(0);
        //用户id
        thumbrecord.setThumbby(userid);
        //点赞文章
        thumbrecord.setThumbtotype(0);
        //点赞id
        thumbrecord.setThumbto(articleid);

        Date currentTime = new Date();

        if(userid==null){
            backResult.setResult(false);
            backResult.setErrMsg("登录信息失效，请重新登录");
        }else if(this.thumbrecordService.isThumbRecordExist(thumbrecord)) {
            backResult.setResult(false);
            backResult.setErrMsg("您已经点过赞了！");
        }else {
            Article newArticle = new Article();
            Article oldArticle = this.articleService.queryById(articleid);

            newArticle.setArticleid(articleid);
            newArticle.setArticlethumb(oldArticle.getArticlethumb() + 1);
            Integer oldArticleHot = (int) Math.round(this.hotTool.calculateArticleHot(oldArticle) * 100000);
            oldArticle.setArticlethumb(oldArticle.getArticlethumb() + 1);

            //更新帖子热度
            Integer articleHot = (int) Math.round(this.hotTool.calculateArticleHot(oldArticle) * 100000);

            newArticle.setArticlehot(articleHot);

            Article backResultArticle = this.articleService.update(newArticle);

            Thumbrecord thumbrecordNew = new Thumbrecord();
            //用户点赞
            thumbrecordNew.setThumbbytype(0);
            //用户id
            thumbrecordNew.setThumbby(userid);
            //点赞文章
            thumbrecordNew.setThumbtotype(0);
            //点赞id
            thumbrecordNew.setThumbto(articleid);
            //time
            thumbrecordNew.setThumbtime(currentTime);

            this.thumbrecordService.insert(thumbrecordNew);

            int deltaHot =  articleHot - oldArticleHot;

            backResult.setResult(true);
            backResult.setResMsg("点赞成功，此次点赞为帖子增加了"+ deltaHot +"热度");
        }

        return backResult.toString();
    }

    @RequestMapping("collectarticle")
    @ResponseBody
    public String collectArticle(HttpSession session,
                                 Integer articleid){
        Integer userid = (Integer) session.getAttribute("uid");
        JsonResult backResult = new JsonResult();
        Collect collect = new Collect();
        //用户id
        collect.setUserid(userid);
        //文章id
        collect.setArticleid(articleid);

        Date currentTime = new Date();

        if(userid==null){
            backResult.setResult(false);
            backResult.setErrMsg("登录信息失效，请重新登录");
        }else if(this.collectService.isCollectExisted(collect)) {
            backResult.setResult(false);
            backResult.setErrMsg("您收藏过了！");
        }else {
            Article newArticle = new Article();
            Article oldArticle = this.articleService.queryById(articleid);

            newArticle.setArticleid(articleid);
            newArticle.setArticlecollect(oldArticle.getArticlecollect() + 1);
            Integer oldArticleHot = (int) Math.round(this.hotTool.calculateArticleHot(oldArticle) * 100000);
            oldArticle.setArticlethumb(oldArticle.getArticlethumb() + 1);

            //更新帖子热度
            Integer articleHot = (int) Math.round(this.hotTool.calculateArticleHot(oldArticle) * 100000);

            newArticle.setArticlehot(articleHot);

            Article backResultArticle = this.articleService.update(newArticle);

            Collect collectNew = new Collect();
            //用户点赞
            collectNew.setCollecttype(1);
            //用户id
            collectNew.setUserid(userid);
            //点赞文章
            collectNew.setArticleid(articleid);
            //time
            collectNew.setCollecttime(currentTime);

            this.collectService.insert(collectNew);
            int deltaHot =  articleHot - oldArticleHot;

            backResult.setResult(true);
            backResult.setResMsg("收藏成功，此次收藏为帖子增加了"+ deltaHot +"热度");
        }

        return backResult.toString();
    }


}
