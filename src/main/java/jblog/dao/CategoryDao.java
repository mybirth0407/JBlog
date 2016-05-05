package jblog.dao;

import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
    @Autowired
    private SqlSession sqlSession;

    public void insertCategory(CategoryVo categoryVo) {
        sqlSession.insert("category.add", categoryVo);
    }
}
