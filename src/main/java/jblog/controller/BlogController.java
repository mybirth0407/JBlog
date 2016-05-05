package jblog.controller;

import jblog.config.Config;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/{id}/blog-admin-basic")
    public String blogAdminBasic(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        return "blog/blog-admin-basic";
    }

    @RequestMapping("/{id}/blog-admin-category")
    public String blogAdminCategroy(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        model.addAttribute("categoryList", categoryService.list(id));
        return "blog/blog-admin-category";
    }

    @RequestMapping("/{id}/blog-admin-write")
    public String blogAdminWrite(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        return "blog/blog-admin-write";
    }

    @RequestMapping("/{id}/blog-main")
    public String blogMain(
        @PathVariable("id") String id,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        model.addAttribute("categoryList", categoryService.list(id));
        return "blog/blog-main";
    }

    @RequestMapping("{id}/changeSettings")
    public String changeSettings(
        @PathVariable("id") String id,
        @RequestParam("title") String blogName,
        @RequestParam("logo-file") MultipartFile logoImg,
        Model model) {
        model.addAttribute("blogVo", blogService.getByID(id));
        System.out.println(id + " " + blogName + " " + logoImg);
        if (logoImg.isEmpty() == false) {
            System.out.println("1");
            String originFileName = logoImg.getOriginalFilename();
            String extName = originFileName.substring(
                originFileName.lastIndexOf(".") + 1,
                originFileName.length());
            System.out.println("2");
            String saveFileName = blogService.generateFileName(extName);
            System.out.println("3");
            blogService.uploadFile(
                logoImg, Config.FILE_SAVE_PATH, saveFileName);
            System.out.println("4");

            String img = "/jblog/product-images/" + saveFileName;
            blogService.changeSettings(id, blogName, img);
            System.out.println("5");
        }
        else {
            blogService.changeSettings(id, blogName, "");
        }
        System.out.println(id + " " + blogName + " " + logoImg);
        return "redirect:/" + id + "/blog-main";
    }
}
