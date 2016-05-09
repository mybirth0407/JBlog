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

    public PostVo insertPost(PostVo postVo) {
        if (sqlSession.insert("post.write", postVo) == 0) {
            return null;
        }
        return postVo;
    }

    public List<PostVo> getCategoryListByID(String id) {
        return sqlSession.selectList("post.getListByID", id);
    }

    public PostVo getRecentByCategoryNo(Long categoryNo) {
        return sqlSession.selectOne(
            "post.getRecentByCategoryNo", categoryNo);
    }

    public List<PostVo> getPostListByCategoryNo(Long categoryNo) {
        return sqlSession.selectList("post.getListByCategoryNo", categoryNo);
    }

    public PostVo getRecentByID(String id) {
        return sqlSession.selectOne("post.getRecentByID", id);
    }

    public PostVo getByPostNo(Long postNo) {
        return sqlSession.selectOne("post.getByPostNo", postNo);
    }

    public void deletePostByPostNo(Long postNo) {
        sqlSession.delete("post.deleteByPostNo", postNo);
    }
}
