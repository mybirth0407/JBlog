package jblog.dao;

import jblog.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDao {
    @Autowired
    private SqlSession sqlSession;
    private PostVo byRecent;

    public PostVo insertPost(PostVo postVo) {
        if (sqlSession.insert("post.write", postVo) == 0) {
            return null;
        }
        return postVo;
    }

    public List<PostVo> getCategoryListByID(String id) {
        return sqlSession.selectList("post.getListByID", id);
    }

    public PostVo getByRecent(Long categoryNo) {
        return sqlSession.selectOne(
            "post.getByRecentByCategoryNo", categoryNo);
    }

    public List<PostVo> getCategoryListByCategoryNo(Long categoryNo) {
        return sqlSession.selectList("post.getListByCategoryNo", categoryNo);
    }
}
