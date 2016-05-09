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
        @ModelAttribute PostVo postVo, @PathVariable("id") String id) {
        postService.write(postVo);
        System.out.println(postVo);
        categoryService.updatePosting(postVo.getCategoryNo());
        return "redirect:/" + id + "/writesuccess/" + postVo.getNo();
    }

    @RequestMapping("/{id}/writesuccess/{postNo}")
    public String writeSuccess(
        @PathVariable("id") String id,
        @PathVariable("postNo") Long postNo,
        Model model) {
        model.addAttribute("postVo", postService.getPostByNo(postNo));
        model.addAttribute("blogVo", blogService.getBlogVoByID(id));
        return "blog/blog-admin-writesuccess";
    }
}
