package jblog.service;

import jblog.dao.CategoryDao;
import jblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public void add(CategoryVo categoryVo) {
        categoryDao.insertCategory(categoryVo);
    }
    
    public List<CategoryVo> list(String id) {
        return categoryDao.getCategoryListByID(id);
    }
}
