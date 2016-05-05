package jblog.controller;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/joinform")
    public String joinform() {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(
        @Valid @ModelAttribute UserVo userVo,
        BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "user/join";
        }

        if (userService.join(userVo) == false) {
            return "user/join";
        }

        BlogVo blogVo = new BlogVo(userVo.getId());
        System.out.println(blogVo);
        blogVo = blogService.add(blogVo);

        System.out.println("");
        System.out.println(blogVo);

        CategoryVo categoryVo = new CategoryVo(blogVo.getNo());
        System.out.println(categoryVo);
        categoryService.add(categoryVo);

        return "redirect:/joinsuccess";
    }

    @RequestMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping("/loginform")
    public String loginform() {
        return "user/login";
    }

    @RequestMapping("/login_fail")
    public String loginFail() {
        return "user/login-fail";
    }
}
