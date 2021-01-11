package com.kalvin.kvf.modules.sys.controller;

import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Package: com.kalvin.kvf.modules.sys.controller
 * @Project: kvf
 * @Author TaoLiang
 * @Date 2021/1/9 15:18
 * @Description
 **/
@RestController
@RequestMapping("member")
public class MemberController extends BaseController {
    @Autowired
    private IUserService userService;
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("member/products");
    }
    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("member/login");
    }
    @GetMapping("products")
    public ModelAndView products() {
        return new ModelAndView("member/products");
    }
}
