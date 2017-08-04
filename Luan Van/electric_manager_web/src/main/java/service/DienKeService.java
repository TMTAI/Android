package service;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import models.DienKe;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import static util.Constant.*;
import static util.Constant.GIOI_TINH;

/**
 * Created by tranm on 29-Jul-17.
 */
public class DienKeService extends DatabaseUtility {
    public Result getDienKe(@Param("makh") String makh) {
        MongoClient mongoClient = new MongoClient();
        JSONArray array = new JSONArray();
        MongoCollection collection = db.getCollection("dienke");
        FindIterable<Document> iterable = collection.find(new Document("makh", makh));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                JSONObject json = new JSONObject();
                json.put(ID, document.getObjectId(MONGO_ID).toHexString());
                json.put(MA_DK, document.getString(MA_DK));
                json.put(MA_KH, document.getString(MA_KH));
                json.put(MA_THANG, document.getString(MA_THANG));
                json.put(MA_NAM, document.getString(MA_NAM));
                json.put(NGAY_DANG_KY, document.getString(NGAY_DANG_KY));
                json.put(CHI_SO_CU, document.getInteger(CHI_SO_CU));
                json.put(CHI_SO_MOI, document.getInteger(CHI_SO_MOI));
                json.put(IS_THANH_TOAN, document.getBoolean(IS_THANH_TOAN));
                array.put(json);
            }
        });
        mongoClient.close();
        return Results.text().render(array);
    }

    public Result updateDienKe(@Param("id") String id,
                               @Param("chisomoi") Integer chisomoi,
                               @Param("isthanhtoan") boolean isthanhtoan) {

        MongoClient mongoClient = new MongoClient();
        MongoCollection collection = db.getCollection(TABLE_DIEN_KE);
        Document doc = new Document(MONGO_ID, new ObjectId(id));
        FindIterable<Document> iter = collection.find(doc);
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                document.replace(CHI_SO_MOI, chisomoi);
                document.replace(IS_THANH_TOAN, isthanhtoan);
                collection.replaceOne(new Document(doc), document);
            }
        });
        mongoClient.close();
        return Results.json().render(doc);
    }

    public Result addDienKe(@Param("madk")String madk,
                            @Param("makh")String makh,
                            @Param("mathang")String mathang,
                            @Param("manam")String manam,
                            @Param("chisocu")Integer chisocu){
        DienKe dienKe = new DienKe();
        dienKe.setMadk(madk);
        dienKe.setMakh(makh);
        dienKe.setMathang(mathang);
        dienKe.setManam(manam);
        dienKe.setChisocu(chisocu);

        MongoClient mongoClient = new MongoClient();
        MongoCollection collection = db.getCollection("dienke");
        Document document = createDocument(dienKe);
        collection.insertOne(document);

        mongoClient.close();
        return Results.redirect("/");
    }

    private Document createDocument(DienKe dienKe){
        Document document = new Document()
                .append(MA_DK, dienKe.getMadk().toUpperCase())
                .append(MA_KH, dienKe.getMakh().toUpperCase())
                .append(MA_THANG, dienKe.getMathang())
                .append(MA_NAM, dienKe.getManam())
                .append(NGAY_DANG_KY, "")
                .append(CHI_SO_CU, dienKe.getChisocu())
                .append(CHI_SO_MOI, 0)
                .append(IS_THANH_TOAN, false);
        return document;
    }
}
