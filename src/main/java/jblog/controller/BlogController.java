package jblog.controller;

import jblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/{id}/blog-admin-basic")
    public String blogAdminBasic(@PathVariable("id") String id) {
        return "blog/blog-admin-basic";
    }

    @RequestMapping("/{id}/blog-admin-category")
    public String blogAdminCategroy(@PathVariable("id") String id) {
        return "blog/blog-admin-category";
    }

    @RequestMapping("/{id}/blog-admin-write")
    public String blogAdminWrite(@PathVariable("id") String id) {
        return "blog/blog-admin-write";
    }

    @RequestMapping("/{id}/blog-main")
    public String blogMain(@PathVariable("id") String id) {
        return "blog/blog-main";
    }
}
