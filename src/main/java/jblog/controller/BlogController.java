package jblog.controller;

import jblog.config.Config;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PostService postService;

    @RequestMapping("/{id}/blog-admin-basic")
    public String blogAdminBasic(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        return "blog/blog-admin-basic";
    }

    @RequestMapping("/{id}/blog-admin-category")
    public String blogAdminCategroy(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        model.addAttribute("categoryList", categoryService.getListByID(id));
        return "blog/blog-admin-category";
    }

    @RequestMapping("/{id}/blog-admin-write")
    public String blogAdminWrite(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        model.addAttribute("categoryList", categoryService.getListByID(id));
        return "blog/blog-admin-write";
    }

    @RequestMapping("/{id}/blog-main")
    public String blogMain(
        @PathVariable("id") String id,
        @RequestParam(
            value = "category_no", required = true, defaultValue = "-1")
            Long categoryNo,
        @RequestParam(
            value = "post_no", required = true, defaultValue = "-1")
            Long postNo,
        Model model) {
        PostVo postVo = null;
        if (postNo == -1 && categoryNo == -1) {
            postVo = postService.getPostRecent(id);
            model.addAttribute("postVo", postVo);
        }
        else if (postNo == -1){
            model.addAttribute("postVo",
                postService.mainPostByCategoryNo(categoryNo));
        }
        else {
            model.addAttribute("postVo",
                postService.mainPostByPostNo(postNo));
        }

        if (categoryNo == -1) {
            if (postVo != null) {
                model.addAttribute("postList",
                    postService.getListByCategoyNo(postVo.getCategoryNo()));
            }
        }
        else {
            model.addAttribute(
                "postList", postService.getListByCategoyNo(categoryNo));
        }
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        model.addAttribute("categoryList", categoryService.getListByID(id));
        return "blog/blog-main";
    }

    @RequestMapping("/{id}/changeSettings")
    public String changeSettings(
        @PathVariable("id") String id,
        @RequestParam("blog-name") String blogName,
        @RequestParam("logo-file") MultipartFile logoImg,
        @RequestParam(
            value = "default-image", required = true, defaultValue = "false")
            Boolean isIMGDefaultTrue,
        Model model) {
        String img = null;
        model.addAttribute("blogVo", blogService.getBlogByID(id));
        if (isIMGDefaultTrue == true) {
            img = Config.DEFAULT_IMG;
        }
        else if (logoImg.isEmpty() == false) {
            String originFileName = logoImg.getOriginalFilename();
            String extName = originFileName.substring(
                originFileName.lastIndexOf(".") + 1,
                originFileName.length());
            String saveFileName = blogService.generateFileName(extName);
            blogService.uploadFile(
                logoImg, Config.FILE_SAVE_PATH, saveFileName);

            img = "/jblog/product-images/" + saveFileName;
        }
        blogService.changeSettings(id, blogName, img);
        return "redirect:/" + id + "/blog-main";
    }

    @RequestMapping(value = "/logo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logoupload(
        MultipartHttpServletRequest multipartHttpServletRequest) {
        Iterator<String> iterator =
            multipartHttpServletRequest.getFileNames();
        String filename = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (iterator.hasNext()) {
            MultipartFile multipartFile =
                multipartHttpServletRequest.getFile(iterator.next());
            filename = blogService.uploadIMG(multipartFile);
            map.put("result", "success");
            map.put("data", filename);
        }
        return map;
    }

    @RequestMapping("/{id}/changeDefault")
    public String changeSettings(
        @PathVariable("id") String id,
        Model model) {
        blogService.changeSettings(
            id, Config.DEFAULT_BLOGNAME, Config.DEFAULT_IMG);
        return "redirect:/" + id + "/blog-admin-basic";
    }
}
