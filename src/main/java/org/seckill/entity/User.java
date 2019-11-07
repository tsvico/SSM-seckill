package org.seckill.entity;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/7 9:44
 * 功能
 */
public class User {
    private int userId;
    private String userName;
    private String userpwd;
    private String userNickname;
    private long userPhone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public long getUserPhone() {
        return userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
