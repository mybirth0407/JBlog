package jblog.dao;

import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private SqlSession sqlSession;

    public boolean insertUser(UserVo userVo) {
        boolean success = true;
        if (sqlSession.insert("user.join", userVo) == 0) {
            success = false;
        }
        return success;
    }

    public boolean loginUser(UserVo userVo) {
        boolean success = true;
        if (sqlSession.selectOne("user.login", userVo) == null) {
            success = false;
        }
        return success;
    }

    public UserVo getUserByID(String id) {
        return sqlSession.selectOne("user.selectByID", id);
    }
}
