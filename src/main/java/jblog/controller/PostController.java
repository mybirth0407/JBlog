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
import org.springframework.web.bind.annotation.RequestParam;

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
        categoryService.updatePosting(postVo.getCategoryNo(), 1L);
        return "redirect:/" + id + "/writesuccess/" + postVo.getNo();
    }

    @RequestMapping("/{id}/writesuccess/{postNo}")
    public String writeSuccess(
        @PathVariable("id") String id,
        @PathVariable("postNo") Long postNo,
        Model model) {
        model.addAttribute("postVo", postService.getPostByPostNo(postNo));
        model.addAttribute("blogVo", blogService.getBlogVoByID(id));
        return "blog/blog-admin-writesuccess";
    }


    @RequestMapping("/{id}/delete")
    public String postDelete(
        @PathVariable("id") String id,
        @RequestParam("category_no") Long categoryNo,
        @RequestParam("post_no") Long postNo) {
        if (categoryNo == null) {
            return "redirect:/" + id + "/blog-main";
        }
        postService.deleteByPostNo(postNo);
        categoryService.updatePosting(categoryNo, -1L);
        return "redirect:/" + id + "/blog-main?category_no=" + categoryNo;
    }
}
