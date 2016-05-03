package jblog.controller;

import jblog.service.UserService;
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
@RequestMapping("/jblog/user")
public class UserController {
    @Autowired
    private UserService userService;

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
        userService.join(userVo);
        return "redirect:/jblog/user/joinsuccess";
    }

    @RequestMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }
}
