package ctu.tmtai.com.models;

import java.sql.Blob;

/**
 * Created by tranm on 30-Jul-17.
 */

public class KhachHang {
    private String makh;
    private String tenkh;
    private String diachi;
    private String makv;
    private String madk;
    private String cmnd;
    private String phone;
    private String gioitinh;
    private Blob avatar;
    private String role;
    private String password;
    private String ngaysinh;

    public KhachHang() {
        this.makh = "";
        this.tenkh = "";
        this.diachi = "";
        this.madk = "";
        this.cmnd = "";
        this.phone = "";
        this.gioitinh = "";
        this.avatar = null;
        this.role = "";
        this.password = "";
        this.ngaysinh = "";
        this.makh = "";
    }

    public KhachHang(String makh, String tenkh, String diachi,  String makv, String madk, String cmnd, String phone, String gioitinh, Blob avatar, String role, String password, String ngaysinh) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.makv = makv;
        this.madk = madk;
        this.cmnd = cmnd;
        this.phone = phone;
        this.gioitinh = gioitinh;
        this.avatar = avatar;
        this.role = role;
        this.password = password;
        this.ngaysinh = ngaysinh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMadk() {
        return madk;
    }

    public void setMadk(String madk) {
        this.madk = madk;
    }

    public String getMakv() {
        return makv;
    }

    public void setMakv(String makv) {
        this.makv = makv;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
