package models;

/**
 * Created by tranm on 29-Jul-17.
 */
public class DienKe {
    private String madk;
    private String makh;
    private String mathang;
    private String manam;
    private String ngaydksd;
    private Integer chisocu;
    private Integer chisomoi;
    private double thanhtien;
    private boolean thanhtoan;

    public DienKe() {
        this.madk = "";
        this.makh = "";
        this.mathang = "";
        this.manam = "";
        this.ngaydksd = "";
        this.chisocu = 0;
        this.chisomoi = 0;
        this.thanhtoan = false;
    }

    public DienKe(String madk, String makh, String mathang, String manam, Integer chisocu) {
        this.madk = madk;
        this.makh = makh;
        this.mathang = mathang;
        this.manam = manam;
        this.chisocu = chisocu;
    }


    public DienKe(String madk, String makh, String mathang, String manam, String ngaydksd, Integer chisocu) {
        this.madk = madk;
        this.makh = makh;
        this.mathang = mathang;
        this.manam = manam;
        this.ngaydksd = ngaydksd;
        this.chisocu = chisocu;
    }

    public DienKe(String madk, String makh, String mathang, String manam, String ngaydksd, Integer chisocu, Integer chisomoi) {
        this.madk = madk;
        this.makh = makh;
        this.mathang = mathang;
        this.manam = manam;
        this.ngaydksd = ngaydksd;
        this.chisocu = chisocu;
        this.chisomoi = chisomoi;
    }

    public String getMadk() {
        return madk;
    }

    public void setMadk(String madk) {
        this.madk = madk;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMathang() {
        return mathang;
    }

    public void setMathang(String mathang) {
        this.mathang = mathang;
    }

    public String getManam() {
        return manam;
    }

    public void setManam(String manam) {
        this.manam = manam;
    }

    public String getNgaydksd() {
        return ngaydksd;
    }

    public void setNgaydksd(String ngaydksd) {
        this.ngaydksd = ngaydksd;
    }

    public Integer getChisocu() {
        return chisocu;
    }

    public void setChisocu(Integer chisocu) {
        this.chisocu = chisocu;
    }

    public Integer getChisomoi() {
        return chisomoi;
    }

    public void setChisomoi(Integer chisomoi) {
        this.chisomoi = chisomoi;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public static double tinhTien(Integer chisomoi, Integer chisocu){
        if (chisomoi < chisocu || chisomoi == 0){
            return 0;
        }
        double sotien = 0;
        int tieuthu = chisomoi - chisocu;
        if (tieuthu> 0 && tieuthu <= 50){
            sotien = 1484 * tieuthu;
        }else if (tieuthu> 50 && tieuthu <= 100){
            sotien = (1484 * 50) +(tieuthu - 50)*1533;
        }else if (tieuthu> 100 && tieuthu <= 200){
            sotien = (1484 * 50) + (1533*50) + (tieuthu-100)*1786;
        }else if (tieuthu> 200 && tieuthu <= 300){
            sotien = (1484 * 50) + (1533*50) + (1786*100) + (tieuthu - 200)*2242;
        }else if (tieuthu> 300 && tieuthu <= 400){
            sotien = (1484 * 50) + (1533*50) + (1786*100) + (2242*100) + (tieuthu-300)*2503;
        }else{
            sotien = (1484 * 50) + (1533*50) + (1786*100) + (2242*100) + (100*2503) +(tieuthu-400)*2587;
        }
        return sotien;
    }

    public boolean isDongtien() {
        return thanhtoan;
    }

    public void setDongtien(boolean dongtien) {
        this.thanhtoan = dongtien;
    }
}
