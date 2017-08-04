package ctu.tranminhtai.ungdung;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by DELL on 9/23/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Tao cac ket noi toi csdl:
    public  static String DB_NAME ="dictionary.db";
    public static  String TABLE_NAME ="dictionary";
    public static  String DIC_ID ="id";
    public static  String WORD ="word";
    public static  String DIC_TYPE ="type";
    private static String DB_PATH ="";
    public static SQLiteDatabase db;
    public Context myContext;
    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,1);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getParent()+"/databases/"+DB_NAME;
        Log.d("LINK"," ĐƯỜNG DẪN : "+ DB_PATH);
    }

    public void createDatabase(){
        try {
            boolean dbExist = checkDataBase();
            if(dbExist){
                Log.d("Create:","Đã Có Database");
            }else{
                //By calling this method and empty database will be created into the default system path
                //of your application so we are gonna be able to overwrite that database with our database.
                this.getReadableDatabase();
                copyDataBase();
            }
        }
        catch (Exception e) {

        }
    }
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            Log.d("CONNECT","Chưa có Database");

        }catch(SQLiteException e){
            //database does't exist yet.
            Log.d("CONNECT","Có Database");
        }

        if(checkDB != null){
            checkDB.close();
        }

        if (checkDB != null) return true;
        else return false;
    }

    public void copyDataBase(){

        try{
            //Open your local db as the input stream
            InputStream myInput =null;
            try{
                myInput = myContext.getAssets().open(DB_NAME);
            }catch(Exception ee){
                Log.d("ERROR", "không có getAsset!");
            }

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(DB_PATH);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
            Log.d("ERROR", "Đã coppy Database!");
        }
        catch (Exception e) {
            Log.d("ERROR", "Update Lỗi " +e.getMessage());
            e.printStackTrace();
            //catch exception
        }
    }

    /**
     *
     * @return database connected!
     * @throws SQLException
     */
    public SQLiteDatabase openDataBase() throws SQLException{

        //Open the database
        db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }
    public void resetDataBase() throws SQLException{

        try{
            //Open your local db as the input stream
            InputStream myInput = myContext.getAssets().open("reset.db");

            // Path to the just created empty db

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(DB_PATH);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e) {
            //Log.d("bat loi", "bat loi" +e.getMessage());
            //catch exception
        }
    }
    @Override
    public synchronized void close() {

        if(db != null)
        { db.close();}

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
