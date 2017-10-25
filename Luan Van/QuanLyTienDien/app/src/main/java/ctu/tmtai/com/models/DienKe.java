package ctu.tmtai.com.models;

import org.json.JSONException;
import org.json.JSONObject;

import static ctu.tmtai.com.util.Constant.*;

/**
 * Created by tranm on 04-Aug-17.
 */

public class DienKe {
    private String id;
    private String madk;
    private String makh;
    private String mathang;
    private String manam;
    private String ngaydksd;
    private Integer chisocu;
    private Integer chisomoi;
    private int thanhtien;
    private boolean thanhtoan;


    public DienKe(JSONObject jsonObject){
        try {
            this.id = jsonObject.getString(ID);
            this.madk = jsonObject.getString(MA_DK);
            this.makh = jsonObject.getString(MA_KH);
            this.mathang = jsonObject.getString(MA_THANG);
            this.manam = jsonObject.getString(MA_NAM);
            this.ngaydksd = jsonObject.getString(NGAY_DANG_KY);
            this.chisocu = jsonObject.getInt(CHI_SO_CU);
            this.chisomoi = jsonObject.getInt(CHI_SO_MOI);
            this.thanhtoan = jsonObject.getBoolean(IS_THANH_TOAN);
            this.thanhtien = jsonObject.getInt(THANH_TIEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public static int tinhTien(Integer chisomoi, Integer chisocu){
        if (chisomoi < chisocu || chisomoi == 0){
            return 0;
        }
        int sotien = 0;
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

    public boolean isThanhtoan() {
        return thanhtoan;
    }

    public void setDongtien(boolean dongtien) {
        this.thanhtoan = dongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setThanhtoan(boolean thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}
