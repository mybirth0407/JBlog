package jblog.controller;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/{id}/post-write")
    public String postWrite(
        @ModelAttribute PostVo postVo, @PathVariable("id") String id,
        String categoryName) {
        postVo.setCategoryNo(
            categoryService.getByCategoryName(categoryName).getNo());
        postService.write(postVo);
        return "redirect:/" + id + "/writesuccess";
    }

    @RequestMapping("{id}/writesuccess")
    public String writeSuccess(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        return "blog/blog-admin-writesuccess";
    }
}
