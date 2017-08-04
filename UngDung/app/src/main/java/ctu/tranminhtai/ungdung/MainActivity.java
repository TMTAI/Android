package ctu.tranminhtai.ungdung;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    protected RelativeLayout backGround;
    protected Button btnPlayGame, btnHuongDan, btnTuDien;
    protected ImageView imgStar;
    protected SharedPreferences preferences;
    protected SharedPreferences.Editor editor;
    protected MediaPlayer mediaPlayer;
    protected String Hoten;
    TextView txtLoiChao;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        preferences=getApplicationContext().getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        editor = preferences.edit();
        //tao Bunder:
        Bundle kqtv = new Bundle();
        kqtv = getIntent().getExtras();
        if(kqtv != null) {
            //luu du lieu vao:
            String ht = kqtv.getString("trave");
            editor.putString("Hoten",ht);
            editor.commit();
            Log.d("Luutru","da luu vao xml");
            mediaPlayer.start();
            Hoten = preferences.getString("Hoten", null);
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    "Nếu bạn chưa nhập tên thì vui lòng nhấn vào ngôi sao để nhập tên",
                    Toast.LENGTH_SHORT
            );
            txtLoiChao.setText("CHÚC BẠN LUÔN VUI VẺ !");
        }

        Hoten = preferences.getString("Hoten", null);
        if (Hoten != null)  txtLoiChao.setText("CHÚC " + Hoten + " LUÔN VUI VẺ !");
        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MHPlaygame = new Intent(MainActivity.this, Playgame.class);
                startActivity(MHPlaygame);
            }
        });


        btnTuDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MHTuDien = new Intent(MainActivity.this, Dictionary.class);
                startActivity(MHTuDien);
            }
        });


        btnHuongDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Phần mềm được thiết kể bởi Trần Minh Tài\n" +
                        "Phiên bản v1.0", Toast.LENGTH_LONG).show();
            }
        });

        imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent MHDangki = new Intent(getApplicationContext(), DangKi.class);
                    startActivity(MHDangki);
                    finish();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void AnhXa(){
        backGround = (RelativeLayout) findViewById(R.id.ManHinhActivity);
        backGround.setBackgroundResource(R.drawable.background19);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.winxpcut);

        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hieuunghinh);
        txtLoiChao = (TextView) findViewById(R.id.textViewLoiChao);
        txtLoiChao.startAnimation(a);

        btnPlayGame = (Button) findViewById(R.id.buttonPlayGame);
        btnPlayGame.setText("Trò Chơi");

        btnTuDien = (Button) findViewById(R.id.buttonTuDien);
        btnTuDien.setText("Từ Điển");

        btnHuongDan = (Button) findViewById(R.id.buttonHuongDan);
        btnHuongDan.setText("Thông Tin");

        imgStar = (ImageView)findViewById(R.id.imageViewStar);
        imgStar.startAnimation(a);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder().setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            android.app.AlertDialog.Builder bd = new android.app.AlertDialog.Builder(MainActivity.this);
            bd.setTitle("Thông báo nhỏ!!");
            bd.setMessage("Bạn muốn thoát Ứng Dụng?");
            //tao button ok:
            android.app.AlertDialog.Builder builder = bd.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    finish();
                }
            });
            bd.setNegativeButton("Tôi đã suy nghĩ lại",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    di.cancel();
                }
            });
            android.app.AlertDialog al=bd.create();
            al.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        finish();
    }
}
