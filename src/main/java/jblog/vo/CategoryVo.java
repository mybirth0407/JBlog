package jblog.vo;

public class CategoryVo {
    private Long no;
    private Long blogNo;
    private Long posting;
    private String categoryName;
    private String description;

    public CategoryVo() {

    }

    public CategoryVo(Long blogNo) {
        this.blogNo = blogNo;
        this.posting = 0L;
        this.categoryName = "insert category";
        this.description = "insert catgory description";
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(Long blogNo) {
        this.blogNo = blogNo;
    }

    public Long getPosting() {
        return posting;
    }

    public void setPosting(Long posting) {
        this.posting = posting;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
            "no=" + no +
            ", blogNo=" + blogNo +
            ", posting=" + posting +
            ", categoryName='" + categoryName + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
