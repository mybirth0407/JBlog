package jblog.service;

import jblog.dao.UserDao;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean join(UserVo userVo) {
        boolean success = true;
        if (userDao.insertUser(userVo) == false) {
            success = false;
        }
        return success;
    }

    public UserVo login(UserVo userVo) {
        if (userDao.loginUser(userVo) == false) {
            return null;
        }
        return userVo;
    }
}
