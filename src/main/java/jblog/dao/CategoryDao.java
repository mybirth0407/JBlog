package jblog.dao;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    @Autowired
    private SqlSession sqlSession;

    public void insertCategory(CategoryVo categoryVo) {
        sqlSession.insert("category.add", categoryVo);
    }

    public List<CategoryVo> getCategoryListByID(String id) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByUserID", id);
        List<CategoryVo> categoryList = sqlSession.selectList(
            "category.listByBlogNo", blogVo.getNo());
        return categoryList;
    }
}
