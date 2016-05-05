package jblog.service;

import jblog.dao.BlogDao;
import jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;

    public BlogVo add(BlogVo blogVo) {
        Long no = blogDao.insertBlog(blogVo);
        BlogVo retBlogVo = blogDao.get(no);
        return retBlogVo;
    }
}
