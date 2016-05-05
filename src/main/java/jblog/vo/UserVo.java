package jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String passwd;
    private String regDate;

    public UserVo() {

    }

    public UserVo(String id, String passwd) {
        this.id = id;
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "UserVo{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", passwd='" + passwd + '\'' +
            ", regDate='" + regDate + '\'' +
            '}';
    }
}
