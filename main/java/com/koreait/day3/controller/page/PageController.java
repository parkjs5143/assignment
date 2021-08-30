package com.koreait.day3.controller.page;

import com.koreait.day3.model.front.AdminMenu;
import com.koreait.day3.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")   // http://127.0.0.1:9090/pages
public class PageController {
    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path={""})
    public ModelAndView index(){    // http/아무것도 를 입력하면 바로 이쪽으로 들어옴 메소드명은 상관없음
        return new ModelAndView("/pages/index")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user");
    }

    @RequestMapping("/adminuser")
    public ModelAndView adminuser() {
        return new ModelAndView("/pages/adminuser")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "adminuser");
    }

    @RequestMapping("/category")
    public ModelAndView category() {
        return new ModelAndView("/pages/category")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category");
    }

    @RequestMapping("/item")
    public ModelAndView item() {
        return new ModelAndView("/pages/item")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "item");
    }

    @RequestMapping("/ordergroup")
    public ModelAndView ordergroup() {
        return new ModelAndView("/pages/ordergroup")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "ordergroup");
    }

    @RequestMapping("/partner")
    public ModelAndView partner() {
        return new ModelAndView("/pages/partner")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "partner");
    }
}