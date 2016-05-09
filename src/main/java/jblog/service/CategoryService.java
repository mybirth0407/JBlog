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

    public CategoryVo add(CategoryVo categoryVo) {
        return categoryDao.insertCategory(categoryVo);
    }
    
    public List<CategoryVo> getListByID(String id) {
        return categoryDao.getCategoryListByID(id);
    }

    public boolean remove(Long categoryNo) {
        return categoryDao.delete(categoryNo);
    }

    public CategoryVo getByCategoryName(String categoryName) {
        return categoryDao.getCategoryByCategoryName(categoryName);
    }

    public void updatePosting(Long categoryNo, Long action) {
        categoryDao.update(categoryNo, action);
    }

    public CategoryVo getByNo(Long categoryNo) {
        return categoryDao.getCategoryByCategoryNo(categoryNo);
    }
}
