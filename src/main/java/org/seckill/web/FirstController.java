package org.seckill.web;

import org.seckill.entity.Goods;
import org.seckill.entity.Seckill;
import org.seckill.service.GoodsService;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        if (seckillId == null)
            return "redirect:/list2";
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/list2";
        }
        Goods goods = goodsService.GetById(seckillId);
        model.addAttribute("goods", goods);
        model.addAttribute("seckill", seckill);
        return "tmalldetail";
    }
}
