package service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import models.DienKe;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import org.bson.Document;

import static util.Constant.*;

public class HoaDonService extends DatabaseUtility {
    public Result getHoaDon(@Param("makh") String makh,
                            @Param("madk") String madk,
                            @Param("mathang") String mathang,
                            @Param("manam") String manam) {

        MongoClient client = new MongoClient();

        Document dienke = find(TABLE_DIEN_KE, madk, makh, mathang, manam);

        Document hoadon = find(TABLE_HOA_DON, madk, makh, mathang, manam);

        MongoCollection collection = db.getCollection(TABLE_HOA_DON);
        Document document = createHoaDonByDienKe(dienke);

        if (hoadon != null && !hoadon.isEmpty()){
            collection.replaceOne(hoadon, document);
        }else{
            collection.insertOne(document);
        }

        hoadon = createHoaDon(dienke);

        client.close();
        return Results.json().render(hoadon);
    }

    private Document find(String tableName, String madk, String makh, String mathang, String manam) {
        Document doc = createDoccument(madk, makh, mathang, manam);

        MongoClient client = new MongoClient();
        MongoCollection dienke = db.getCollection(tableName);
        Document document = (Document) dienke.find(doc).first();
        client.close();
        return document;
    }

    private Document createHoaDon(Document document) {
        String mahd = HOA_DON + document.getString(MA_KH) + document.getString(MA_THANG) + document.getString(MA_NAM);
        int vat = 10;
        double thanhtien = DienKe.tinhTien(document.getInteger(CHI_SO_MOI), document.getInteger(CHI_SO_CU));
        double thanhtoan = thanhtien * vat / 100 + thanhtien;
        Document khachhang = db.getCollection(TABLE_KHACH_HANG).find(new Document(MA_KH, document.getString(MA_KH))).first();
        String diachi = UserService.createDiaChi(khachhang.getString(DIA_CHI), khachhang.getString(MA_KHU_VUC));
        Document hoadon = new Document()
                .append(MA_HD, mahd)
                .append(MA_KH, document.getString(MA_KH))
                .append(TEN_KH, khachhang.getString(TEN_KH))
                .append(DIA_CHI, diachi)
                .append(MA_DK, document.getString(MA_DK))
                .append(TIEU_THU, document.getInteger(CHI_SO_MOI) - document.getInteger(CHI_SO_CU))
                .append(TU_NGAY, document.getString(MA_THANG) + "/" + document.getString(MA_NAM))
                .append(DEN_NGAY, document.getString(MA_THANG) + "/" + document.getString(MA_NAM))
                .append(VAT, vat)
                .append(MA_THUE, "TKHSERU5474871117SF")
                .append(THANH_TIEN, thanhtien)
                .append(IS_THANH_TOAN, document.getBoolean(IS_THANH_TOAN))
                .append(THANH_TOAN, thanhtoan);
        return hoadon;
    }

    private Document createHoaDonByDienKe(Document document) {
        String mahd = HOA_DON + document.getString(MA_KH) + document.getString(MA_THANG) + document.getString(MA_NAM);
        int vat = 10;
        double thanhtien = DienKe.tinhTien(document.getInteger(CHI_SO_MOI), document.getInteger(CHI_SO_CU));
        double thanhtoan = thanhtien * vat / 100 + thanhtien;
        Document hoadon = new Document()
                .append(MA_HD, mahd)
                .append(MA_KH, document.getString(MA_KH))
                .append(MA_DK, document.getString(MA_DK))
                .append(MA_THANG, document.getString(MA_THANG))
                .append(MA_NAM, document.getString(MA_NAM))
                .append(TU_NGAY, document.getString(MA_THANG) + "/" + document.getString(MA_NAM))
                .append(DEN_NGAY, document.getString(MA_THANG) + "/" + document.getString(MA_NAM))
                .append(VAT, vat)
                .append(MA_THUE, "TKHSERU5474871117SF")
                .append(THANH_TIEN, thanhtien)
                .append(THANH_TOAN, thanhtoan)
                .append(TIEU_THU, document.getInteger(CHI_SO_MOI) - document.getInteger(CHI_SO_CU));
        return hoadon;
    }
    private Document createDoccument(String madk, String makh, String mathang, String manam) {
        Document doc = new Document()
                .append(MA_DK, madk)
                .append(MA_KH, makh)
                .append(MA_THANG, mathang)
                .append(MA_NAM, manam);
        return doc;
    }
}
