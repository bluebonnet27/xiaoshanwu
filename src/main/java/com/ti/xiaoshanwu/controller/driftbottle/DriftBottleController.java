package com.ti.xiaoshanwu.controller.driftbottle;

import com.ti.xiaoshanwu.entity.Driftbottle;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.DriftbottleImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.DriftbottleService;
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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        Driftbottle siftDriftbottle = new Driftbottle();
        long bottlesCount = this.driftbottleService.getCount(siftDriftbottle);
        model.addAttribute("countAll",bottlesCount);
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
                model.addAttribute("bottletitle","?????????????????????");
                model.addAttribute("bottlesubtitle","????????????????????????");
                model.addAttribute("imgsp","https://s3.bmp.ovh/imgs/2022/03/623c86d6bac28a74.jpg");
                return "driftbottle/bottlegetnone";
            }else {
                DriftbottleImpl driftbottleImplGet =
                        this.driftbottleService.convertToDriftbottleImpl(driftbottleGet);
                model.addAttribute("driftbottleimpl",driftbottleImplGet);
                return "driftbottle/bottleget";
            }
        }else {
            model.addAttribute("errtitle","??????????????????");
            model.addAttribute("errsubtitle","????????????session???????????????????????????????????????????????????");
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

            model.addAttribute("bottletitle","???????????????");
            model.addAttribute("bottlesubtitle","id???"+bottleid);
            model.addAttribute("imgsp","https://s3.bmp.ovh/imgs/2022/03/623c86d6bac28a74.jpg");
            return "driftbottle/bottlegetnone";
        }else {
            model.addAttribute("errtitle","??????????????????");
            model.addAttribute("errsubtitle","????????????session???????????????????????????????????????????????????");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("deletebottle")
    public String deleteBottle(Model model,HttpSession session,Integer bottleid){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);
            Boolean isDeleteSuccess = this.driftbottleService.deleteById(bottleid);

            model.addAttribute("bottletitle","???????????????");
            model.addAttribute("bottlesubtitle","id???"+bottleid);
            model.addAttribute("imgsp","https://s3.bmp.ovh/imgs/2022/03/623c86d6bac28a74.jpg");
            return "driftbottle/bottlegetnone";
        }else {
            model.addAttribute("errtitle","??????????????????");
            model.addAttribute("errsubtitle","????????????session???????????????????????????????????????????????????");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
    }

    @RequestMapping("tobottlemy")
    public String toDriftBottleMy(Model model,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  HttpSession session){
        Integer userid = (Integer) session.getAttribute("uid");
        if(userid!=null){
            User nowUser = this.userService.queryById(userid);
            model.addAttribute("user",nowUser);

            //??????
            page = page -1;
            Driftbottle siftDriftbottle = new Driftbottle();
            siftDriftbottle.setBottleacceptid(userid);
            //????????????
            Sort sort = Sort.by(Sort.Order.desc("bottleid"));
            //????????????
            PageRequest pageRequest = PageRequest.of(page, limit, sort);

            Page<Driftbottle> pages = this.driftbottleService.queryByPage(siftDriftbottle,pageRequest);
            List<Driftbottle> driftbottles = pages.getContent();

            ArrayList<DriftbottleImpl> driftbottlesImpl = new ArrayList<>();

            for (Driftbottle driftbottle:driftbottles){
                DriftbottleImpl driftbottleAdd = this.driftbottleService.convertToDriftbottleImpl(driftbottle);
                driftbottlesImpl.add(driftbottleAdd);
            }

            model.addAttribute("pages",pages);
            model.addAttribute("driftbottles",driftbottlesImpl);

            return "driftbottle/bottlemy";
        }else {
            model.addAttribute("errtitle","??????????????????");
            model.addAttribute("errsubtitle","????????????session???????????????????????????????????????????????????");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
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

    @RequestMapping("bottlethrow")
    @ResponseBody
    public String driftBottleThrow(HttpSession session,
                                   String bottlecontent){
        Integer userid = (Integer) session.getAttribute("uid");
        JsonResult insertBottleResult = new JsonResult();
        Date nowDate = new Date();
        if(userid==null){
            insertBottleResult.setResult(false);
            insertBottleResult.setErrMsg("?????????????????????????????????????????????");
        }else {
            Driftbottle driftbottle = new Driftbottle();

            driftbottle.setBottlestate(0);
            driftbottle.setBottlecontent(bottlecontent);
            driftbottle.setBottletime(nowDate);
            driftbottle.setBottlesendid(userid);

            Driftbottle sendBottle = this.driftbottleService.insert(driftbottle);

            insertBottleResult.setResult(true);
            insertBottleResult.setResMsg("???????????????????????????"+sendBottle.getBottletime().toString());
        }
        return insertBottleResult.toString();
    }

    @RequestMapping("bottlethrowspe")
    @ResponseBody
    public String driftBottleThrowSpe(HttpSession session,
                                      String bottlecontent,
                                      Integer targetid){
        Integer userid = (Integer) session.getAttribute("uid");
        JsonResult insertBottleResult = new JsonResult();
        Date nowDate = new Date();
        if(userid==null){
            insertBottleResult.setResult(false);
            insertBottleResult.setErrMsg("?????????????????????????????????????????????");
        }else {
            Driftbottle driftbottle = new Driftbottle();

            driftbottle.setBottlestate(1);
            driftbottle.setBottlecontent(bottlecontent);
            driftbottle.setBottletime(nowDate);
            driftbottle.setBottlesendid(userid);
            driftbottle.setBottleacceptid(targetid);

            Driftbottle sendBottle = this.driftbottleService.insert(driftbottle);

            insertBottleResult.setResult(true);
            insertBottleResult.setResMsg("??????????????????????????????????????????????????????????????????????????????????????????"+sendBottle.getBottletime().toString());
        }
        return insertBottleResult.toString();
    }
}
