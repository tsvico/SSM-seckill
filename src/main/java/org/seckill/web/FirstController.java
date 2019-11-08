package org.seckill.web;

import com.alibaba.fastjson.JSONObject;
import org.seckill.entity.Goods;
import org.seckill.entity.Seckill;
import org.seckill.entity.User;
import org.seckill.service.GoodsService;
import org.seckill.service.SeckillService;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/29 10:18
 * 功能
 */
@Controller
public class FirstController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SeckillService seckillService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        //return "forward:list";
        return "redirect:list2";
    }
    @GetMapping("/list2")
    public String list(Model model) {
        List<Goods> list = goodsService.GetAllGoods();
        model.addAttribute("lists", list);
        System.out.println("请求List");
        return "tmalllist";
    }

    @GetMapping("/{seckillId}/detail2")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/list2";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/list2";
        }
        Goods goods = goodsService.GetById(seckillId);
        model.addAttribute("goods", goods);
        model.addAttribute("seckill", seckill);
        return "tmalldetail";
    }
    @GetMapping("/{seckillId}/shopping")
    public String shoping(@PathVariable("seckillId") Long seckillId, Model model,HttpSession session) {
        if (seckillId == null) {
            return "redirect:/list2";
        }
        User user = (User)session.getAttribute("user");
        if (user!=null){
            int result = seckillService.getSeckillByIdandPhone(seckillId,user.getUserPhone());
            if (result==1){
                Goods goods = goodsService.GetById(seckillId);
                model.addAttribute("goods", goods);
                return "tmallshopping";
            }else {
                return "redirect:/"+seckillId+"/detail2";
            }
        }
        return "redirect:/list2";
    }

    @RequestMapping(value = "/getUser",produces = " text/html;charset=UTF-8")
    @ResponseBody
    public String getUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session){
        JSONObject jsonDate = new JSONObject();
        System.out.println(username+password);
        //校验密码
        User user = userService.checkUser (username, password);
        if (user!=null){
            jsonDate.put("code",1);
            jsonDate.put("message","验证通过");
            user.setUserpwd("");
            session.setAttribute("user",user);
        }else {
            jsonDate.put("code",0);
            jsonDate.put("message","用户名或者密码错误");
        }
        return jsonDate.toJSONString();
    }
}
