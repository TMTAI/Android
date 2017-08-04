package models;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.Blob;

import static util.Constant.*;
import static util.Constant.TEN_TINH_TP;

/**
 * Created by tranm on 30-Jul-17.
 */
public class Users {
    private String code;
    private String password;
    private String name;
    private String birthday;
    private String address;
    private String cmnd;
    private String phone;
    private boolean isAdmin;
    private String role;
    private String gender;
    private Blob avatar;

    public Users() {
        this.code = "";
        this.password = "";
        this.name = "";
        this.birthday = "";
        this.address = "";
        this.cmnd = "";
        this.phone = "";
        this.isAdmin = false;
        this.role = "";
        this.gender = "";
        this.avatar = null;
    }

    public Users(String code, String password, String name, String birthday, String address, String cmnd, String phone, boolean isAdmin, String role, String gender, Blob avatar) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

}
