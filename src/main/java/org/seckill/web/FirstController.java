package org.seckill.web;

import org.seckill.entity.Goods;
import org.seckill.entity.Seckill;
import org.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    public String index(){
        //return "forward:/seckill/list";
        return "redirect:list2";
    }
    @GetMapping("/list2")
    public String list(Model model) {
        List<Goods> list = goodsService.GetAllGoods();
        model.addAttribute("lists", list);
        System.out.println("请求List");
        return "tmalllist";
    }
}
