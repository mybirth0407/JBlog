package jblog.controller;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BlogService blogService;

    @RequestMapping("/category-delete")
    @ResponseBody
    public Map<String, Object> categoryDelete(
        @RequestParam("category_no") Long categoryNo) {
        System.out.println(categoryNo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (categoryService.getByNo(categoryNo).getPosting() > 0) {
            map.put("result", "false");
        }
        else {
            map.put("result", "success");
            map.put("data", categoryService.remove(categoryNo));
        }
        return map;
    }

    @RequestMapping("/{id}/category-insert")
    @ResponseBody
    public Map<String, Object> categoryInsert(
        @ModelAttribute CategoryVo categoryVo,
        @PathVariable("id") String id,
        Model model) {
        System.out.println(categoryVo);
        BlogVo blogVo = blogService.getBlogByID(id);
        categoryVo.setBlogNo(blogVo.getNo());
        categoryVo.setPosting(0L);
        model.addAttribute("blogVo", blogVo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", categoryService.add(categoryVo));
        return map;
    }

    @RequestMapping("/{id}/category-list")
    @ResponseBody
    public Map<String, Object> categoryList(
        @PathVariable("id") String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", categoryService.getListByID(id));
        return map;
    }


}
