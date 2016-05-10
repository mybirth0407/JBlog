package jblog.controller;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.PostVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log LOG = LogFactory.getLog(BlogController.class);

    @RequestMapping("/{id}/post-write")
    public String postWrite(
        @ModelAttribute PostVo postVo, @PathVariable("id") String id) {
        postService.write(postVo);
        categoryService.updatePosting(postVo.getCategoryNo(), 1L);
        LOG.debug("post write debug-level");
        return "redirect:/" + id + "/writesuccess/" + postVo.getNo();
    }

    @RequestMapping("/{id}/writesuccess/{postNo}")
    public String postWriteSuccess(
        @PathVariable("id") String id,
        @PathVariable("postNo") Long postNo,
        Model model) {
        model.addAttribute("postVo", postService.getPostByPostNo(postNo));
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        LOG.debug("post write success debug-level");
        return "blog/blog-admin-writesuccess";
    }


    @RequestMapping("/{id}/delete")
    public String postDelete(
        @PathVariable("id") String id,
        @RequestParam("category_no") Long categoryNo,
        @RequestParam("post_no") Long postNo) {
        if (categoryNo != null) {
            postService.deleteByPostNo(postNo);
            categoryService.updatePosting(categoryNo, -1L);
        }
        LOG.debug("post delete debug-level");
        return "redirect:/" + id + "/blog-main";
//        return "redirect:/" + id + "/blog-main?category_no=" + categoryNo;
    }
}
