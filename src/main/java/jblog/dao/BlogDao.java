package jblog.dao;

import jblog.vo.BlogVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BlogDao {
    @Autowired
    private SqlSession sqlSession;

    public BlogVo insertBlog(BlogVo blogVo) {
        sqlSession.insert("blog.add", blogVo);
        return blogVo;
    }

    public BlogVo getBlogVoByNo(Long no) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByNo", no);
        return blogVo;
    }

    public BlogVo getBlogVoByID(String id) {
        BlogVo blogVo = sqlSession.selectOne("blog.selectByUserID", id);
        return blogVo;
    }

    public void updateSettings(String id, String blogName, String img) {
        Map<String, Object> map  = new HashMap<String, Object>();
        BlogVo blogVo = getBlogVoByID(id);
        map.put("id", id);
        if ("".equals(blogName) || blogName == null) {
            blogName = blogVo.getBlogName();
        }
        map.put("blogName", blogName);

        if ("".equals(img) || img == null) {
            img = blogVo.getImg();
        }
        map.put("img", img);
        sqlSession.update("blog.changeSettings", map);
    }
}
