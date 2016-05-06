package jblog.service;

import jblog.dao.PostDao;
import jblog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;

    public PostVo write(PostVo postVo) {
        return postDao.insertPost(postVo);
    }

    public List<PostVo> getListByID(String id) {
        return postDao.getCategoryListByID(id);
    }

    public PostVo mainPostByCategoryNo(Long categoryNo) {
        return postDao.getRecentByCategoryNo(categoryNo);
    }

    public List<PostVo> getListByCategoyNo(Long categoryNo) {
        return postDao.getCategoryListByCategoryNo(categoryNo);
    }
    
    public PostVo getRecent(String id) {
        return postDao.getRecentByID(id);
    }

    public PostVo mainPostByPostNo(Long postNo) {
        return postDao.getByPostNo(postNo);
    }
}
