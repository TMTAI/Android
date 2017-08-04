package ctu.tmtai.com.models;

import java.sql.Blob;

import static ctu.tmtai.com.util.Constant.ROLE_EMPLOYEE;

/**
 * Created by tranm on 10-Jul-17.
 */

public class User {
    private String code;
    private String password;
    private String name;
    private String birthday;
    private String address;
    private String gender;
    private String cmnd;
    private String phone;
    private boolean isAdmin;
    private String role;
    private Blob avatar;

    public User() {
        this.code = "";
        this.password = "";
        this.name = "";
        this.birthday = "";
        this.address = "";
        this.gender = "";
        this.cmnd = "";
        this.phone = "";
        this.isAdmin = false;
        this.role = ROLE_EMPLOYEE;
        this.avatar = null;
    }

    public User(String code, String password, String name, String birthday, String address,String cmnd, String phone, boolean isAdmin, String role,  String gender, Blob avatar) {
        this.code = code;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.cmnd = cmnd;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.role = role;
        this.gender = gender;
        this.avatar = avatar;
    }

    public User(String code, String password, String name, String birthday, String address, String gender, String cmnd, String phone, boolean isAdmin, String role) {
        this.code = code;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.cmnd = cmnd;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }
}
