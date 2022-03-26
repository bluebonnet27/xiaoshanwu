package com.ti.xiaoshanwu.controller.driftbottle;

import com.ti.xiaoshanwu.entity.Driftbottle;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.DriftbottleImpl;
import com.ti.xiaoshanwu.service.DriftbottleService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("bottle")
public class DriftBottleController {
    @Resource
    private DriftbottleService driftbottleService;

    @Resource
    private UserService userService;

    @RequestMapping("tobottleindex")
    public String toDriftBottleIndex(Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);
        }
        return "driftbottle/bottleindex";
    }

    @RequestMapping("tobottleget")
    public String toDriftBottleGet(Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);

            Driftbottle driftbottleGet = this.driftbottleService.queryRandom(1);
            if(driftbottleGet==null){
                model.addAttribute("bottletitle","暂时捞不到瓶子");
                model.addAttribute("bottlesubtitle","过一会再来试试吧");
                model.addAttribute("imgsp","https://s3.bmp.ovh/imgs/2022/03/623c86d6bac28a74.jpg");
                return "driftbottle/bottlegetnone";
            }else {
                DriftbottleImpl driftbottleImplGet =
                        this.driftbottleService.convertToDriftbottleImpl(driftbottleGet);
                model.addAttribute("driftbottleimpl",driftbottleImplGet);
                return "driftbottle/bottleget";
            }
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("collectbottle")
    public String collectBottle(Model model,HttpSession session,Integer bottleid){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);

            Driftbottle driftbottleNew = new Driftbottle();
            driftbottleNew.setBottleid(bottleid);
            driftbottleNew.setBottleacceptid(userid);
            driftbottleNew.setBottlestate(1);
            Driftbottle backBottle = this.driftbottleService.update(driftbottleNew);

            model.addAttribute("bottletitle","收藏成功！");
            model.addAttribute("bottlesubtitle","id："+bottleid);
            model.addAttribute("imgsp","https://s3.bmp.ovh/imgs/2022/03/623c86d6bac28a74.jpg");
            return "driftbottle/bottlegetnone";
        }else {
            model.addAttribute("errtitle","用户信息失效");
            model.addAttribute("errsubtitle","您存储于session中的用户信息已经失效，请重新登录！");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("tobottlemy")
    public String toDriftBottleMy(Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);
        }
        return "driftbottle/bottlemy";
    }

    @RequestMapping("tobottlethrow")
    public String toDriftBottleThrow(Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);
        }
        return "driftbottle/bottlethrow";
    }
}
