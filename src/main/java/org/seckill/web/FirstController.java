package org.seckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/29 10:18
 * 功能
 */
@Controller
public class FirstController {

    @GetMapping("/")
    public String index(){
        //return "forward:/seckill/list";
        return "redirect:list";
    }
}
