package ctu.tranminhtai.ungdung;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Dictionary extends AppCompatActivity {
    ListView lvDictionary;
    ImageButton imgbBack;
    EditText edtSearch;
    RelativeLayout relativeLayout;
    Handler handler = new Handler();
   /* List<String> list;
    ArrayAdapter adapter; */
    ArrayList<Words> list = new ArrayList<>();
    ArrayList listLuu = new ArrayList<>();
    ListAdapter adapter;
    ArrayAdapter<String> adapterLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        AnhXa();

        //Nut Back
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },500);
            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.createDatabase();
        String[] col={"id","word"};
        final SQLiteDatabase query = dbHelper.openDataBase();
        Cursor cursor = query.query("dictionary",col,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String word = cursor.getString(1);
            String nghia;
            String sql = "select * from dictionary where word = '"+ word +"';";
            final Cursor kq = query.rawQuery(sql,null);
            kq.moveToFirst();
            nghia = "Nghĩa: " + kq.getString(3).toString();
            list.add(new Words(word,nghia));
            cursor.moveToNext();
        }
        adapter = new ListAdapter(
                getApplicationContext(),
                R.layout.activity_dong_dictionary,
                list
        );
        lvDictionary.setAdapter(adapter);


        //Tao su kien thay doi:
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String chuoi = edtSearch.getText().toString();
                String sql = "Select * from dictionary where word like '%" + chuoi + "%'";
                Cursor cursor1 = query.rawQuery(sql, null);
                cursor1.moveToFirst();
                list = new ArrayList<>();
                while (!cursor1.isAfterLast()) {
                    String word = cursor1.getString(1);
                    String nghia;
                    final Cursor kq = query.rawQuery(sql,null);
                    kq.moveToFirst();
                    nghia = "Nghĩa: " + kq.getString(3).toString();
                    list.add(new Words(word,nghia));
                    cursor1.moveToNext();
                }
                adapter = new ListAdapter(
                        getApplicationContext(),
                        R.layout.activity_dong_dictionary,
                        list
                );
                lvDictionary.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                lvDictionary.setAdapter(adapter);
            }
        });

        lvDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Vocabulary.class);
                intent.putExtra("Bạn Chọn",list.get(position).getWord());
                startActivity(intent);
            }
        });
    }

    public void AnhXa(){
        lvDictionary = (ListView)findViewById(R.id.ListViewDictionary);
        imgbBack = (ImageButton)findViewById(R.id.ImageButtonBack);
        edtSearch = (EditText)findViewById(R.id.EditTextSearch);
        relativeLayout = (RelativeLayout)findViewById(R.id.RelativeLayoutDictionary);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd = new AlertDialog.Builder(Dictionary.this);
            bd.setTitle("Thông báo nhỏ!!");
            bd.setMessage("Bạn muốn thoát??");
            //tao button ok:
            AlertDialog.Builder builder = bd.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    finish();
                }
            });
            bd.setNegativeButton("Không",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    di.cancel();
                }
            });
            AlertDialog al=bd.create();
            al.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
