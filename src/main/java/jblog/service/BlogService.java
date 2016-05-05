package jblog.service;

import jblog.dao.BlogDao;
import jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Calendar;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;

    public BlogVo add(BlogVo blogVo) {
        Long no = blogDao.insertBlog(blogVo);
        BlogVo retBlogVo = blogDao.getBlogVoByNo(no);
        return retBlogVo;
    }

    public BlogVo getByID(String id) {
        BlogVo blogVo = blogDao.getBlogVoByID(id);
        return blogVo;
    }

    public boolean uploadFile(
        MultipartFile multipartFile,
        String fileSavePath,
        String saveFileName) {
        boolean success = true;
        FileOutputStream fileOutputStream = null;
        try {
            byte fileData[] = multipartFile.getBytes();
            fileOutputStream = new FileOutputStream(
                fileSavePath + "/" + saveFileName);
            fileOutputStream.write(fileData);
        }
        catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return success;
        }
    }

    public String generateFileName(String extName) {
        Calendar calendar = Calendar.getInstance();
        String fileName = "";
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += "." + extName;
        return fileName;
    }

    public void changeSettings(String id, String blogName, String img) {
        blogDao.update(id, blogName, img);
    }
}
