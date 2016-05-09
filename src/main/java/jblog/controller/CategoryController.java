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

    @RequestMapping("/category-delete/{no}")
    @ResponseBody
    public Map<String, Object> categoryDelete(
        @PathVariable("no") Long no) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (categoryService.getByNo(no).getPosting() > 0) {
            map.put("result", "false");
        }
        else {
            map.put("result", "success");
            map.put("data", categoryService.remove(no));
        }
        return map;
    }

    @RequestMapping("/{id}/category-insert")
    @ResponseBody
    public Map<String, Object> categoryInsert(
        @ModelAttribute CategoryVo categoryVo,
        @PathVariable("id") String id,
        Model model) {
        BlogVo blogVo = blogService.getBlogVoByID(id);
        categoryVo.setBlogNo(blogVo.getNo());
        categoryVo.setPosting(0L);
        model.addAttribute("blogVo", blogVo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", categoryService.add(categoryVo));
        return map;
    }
}
