package com.fuful.domain.admin;

/**
 * Created by SunRuiBin on 2019/11/7.
 */
public class User {
    private int id;

    private String uname;
    private String password;


    public String getUname() {

        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String uname, String password) {
        this.id = id;
        this.uname = uname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
