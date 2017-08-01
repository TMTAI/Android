/**
 * Copyright (C) 2013 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import service.DatabaseUtility;

import java.util.ArrayList;
import java.util.List;
import static util.Constant.*;

@Singleton
public class ApplicationController extends DatabaseUtility {
    Logger logger = Logger.getLogger(ApplicationController.class);

    public Result index() {

        return Results.html();

    }


    public Result getKhuVuc() {
        MongoClient mongoClient = new MongoClient();
        MongoCollection<Document> collection = db.getCollection("khuvuc");
        FindIterable<Document> iterable = collection.find();
        final MongoCollection<Document> thanhpho = db.getCollection("tinh_thanhpho");
        JSONArray array = new JSONArray();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                FindIterable<Document> iter = thanhpho.find(new Document("matp", document.getString(MA_THANH_PHO)));
                MongoCursor<Document> curso = iter.iterator();
                if (curso.hasNext()) {
                    document.append("tentp", curso.next().getString("tentp"));
                }
                JSONObject json = new JSONObject();
                json.put(ID, document.getObjectId(MONGO_ID).toHexString());
                json.put(MA_KHU_VUC, document.getString(MA_KHU_VUC));
                json.put(TEN_KHU_VUC, document.getString(TEN_KHU_VUC));
                json.put(MA_THANH_PHO, document.getString(MA_THANH_PHO));
                array.put(json);
            }
        });
        mongoClient.close();
        return Results.text().render(array);
    }

    public Result getThanhPho() {
        MongoClient mongoClient = new MongoClient();
        MongoCollection<Document> collection = db.getCollection("tinh_thanhpho");
        FindIterable<Document> iterable = collection.find();
        JSONArray array = new JSONArray();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                JSONObject json = new JSONObject();
                json.put(ID, document.getObjectId(MONGO_ID).toHexString());
                json.put(MA_THANH_PHO, document.getString(MA_THANH_PHO));
                json.put(TEN_TINH_TP, document.getString(TEN_TINH_TP));
                array.put(json);
            }
        });
        mongoClient.close();
        return Results.text().render(array);
    }
}