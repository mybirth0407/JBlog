package jblog.dao;

import jblog.vo.BlogVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
    @Autowired
    private SqlSession sqlSession;

    public Long insertBlog(BlogVo blogVo) {
        sqlSession.insert("blog.add", blogVo);
        return blogVo.getNo();
    }

    public BlogVo getBlogVoByNo(Long no) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByNo", no);
        return blogVo;
    }

    public BlogVo getBlogVoByID(String id) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByUserID", id);
        return blogVo;
    }
}
