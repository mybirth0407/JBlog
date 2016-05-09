package jblog.service;

import jblog.config.Config;
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
        return blogDao.insertBlog(blogVo);
    }

    public BlogVo getBlogVoByID(String id) {
        return blogDao.getBlogVoByID(id);
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
        blogDao.updateSettings(id, blogName, img);
    }

    public String uploadIMG(MultipartFile multipartFile) {
        if (multipartFile.isEmpty() == false) {
            String fileOriginalName = multipartFile.getOriginalFilename();
            String extName = fileOriginalName.substring(
                fileOriginalName.lastIndexOf(".") + 1,
                fileOriginalName.length());
            String saveFileName = generateFileName(extName);

            uploadFile(multipartFile, Config.FILE_SAVE_PATH, saveFileName);

            String imageUrl = "/jblog/product-images/" + saveFileName;
            return imageUrl;
        }
        return "";

    }
}
