package models;

public class HoaDon {
    private String mahd;
    private String makh;
    private String tenkh;
    private String diachi;
    private String madk;
    private Integer tieuthu;
    private String tungay;
    private String denngay;
    private Integer VAT;
    private String mathue;
    private double thanhtien;
    private double phaitra;

    public HoaDon() {
        this.mahd = "";
        this.makh = "";
        this.tenkh = "";
        this.diachi = "";
        this.madk = "";
        this.tieuthu = 0;
        this.tungay = "1/1/1990";
        this.denngay = "31/1/1990";
        this.VAT = 0;
        this.mathue = "";
        this.thanhtien = 0;
        this.phaitra = 0;
    }

    public HoaDon(String mahd, String makh, String tenkh, String diachi, String madk, Integer tieuthu, String tungay, String denngay, Integer VAT, String mathua, double thanhtien, double phaitra) {
        this.mahd = mahd;
        this.makh = makh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.madk = madk;
        this.tieuthu = tieuthu;
        this.tungay = tungay;
        this.denngay = denngay;
        this.VAT = VAT;
        this.mathue = mathua;
        this.thanhtien = thanhtien;
        this.phaitra = phaitra;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
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

    public Integer getTieuthu() {
        return tieuthu;
    }

    public void setTieuthu(Integer tieuthu) {
        this.tieuthu = tieuthu;
    }

    public String getTungay() {
        return tungay;
    }

    public void setTungay(String tungay) {
        this.tungay = tungay;
    }

    public String getDenngay() {
        return denngay;
    }

    public void setDenngay(String denngay) {
        this.denngay = denngay;
    }

    public Integer getVAT() {
        return VAT;
    }

    public void setVAT(Integer VAT) {
        this.VAT = VAT;
    }

    public String getMathua() {
        return mathue;
    }

    public void setMathua(String mathua) {
        this.mathue = mathua;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public double getPhaitra() {
        return phaitra;
    }

    public void setPhaitra(double phaitra) {
        this.phaitra = phaitra;
    }
}
