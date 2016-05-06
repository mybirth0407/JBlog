package jblog.dao;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {
    @Autowired
    private SqlSession sqlSession;

    public CategoryVo insertCategory(CategoryVo categoryVo) {
        if (sqlSession.insert("category.add", categoryVo) == 0) {
            return null;
        }
        return categoryVo;
    }

    public List<CategoryVo> getCategoryListByID(String id) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByUserID", id);
        List<CategoryVo> categoryList = sqlSession.selectList(
            "category.getListByBlogNo", blogVo.getNo());
        return categoryList;
    }
    
    public boolean delete(Long no) {
        boolean success = true;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("no", no);
        if (sqlSession.delete("category.deleteByNo", map) == 0) {
            success = false;
        }
        return success;
    }

    public CategoryVo getCategoryVoByCategoryName(String categoryName) {
        return sqlSession.selectOne(
            "category.getByCategoryName", categoryName);
    }

    public void update(Long no) {
        sqlSession.update("category.updatePosting", no);
    }
}
