package jblog.vo;

public class BlogVo {
    private Long no;
    private String userID;
    private String blogName;
    private String img;

    public BlogVo() {

    }

    public BlogVo(String userID) {
        this.userID = userID;
        this.blogName = "insert blog name";
        this.img =
            "http://www.rwn.co.kr/news/photo/201405/23271_4367_3147.jpg";
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "BlogVo{" +
            "no=" + no +
            ", userID='" + userID + '\'' +
            ", blogName='" + blogName + '\'' +
            ", img='" + img + '\'' +
            '}';
    }
}
