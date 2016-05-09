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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
        blogVo = blogService.add(blogVo);

        CategoryVo categoryVo = new CategoryVo(blogVo.getNo());
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

    @RequestMapping("/checkID")
    @ResponseBody
    public Map<String, Object> checkID(
        @RequestParam(value = "blog-id", required = true, defaultValue = "")
        String id) {
        UserVo userVo = userService.getUserByID(id);
        System.out.println(userVo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", userVo == null);
        return map;
    }
}
