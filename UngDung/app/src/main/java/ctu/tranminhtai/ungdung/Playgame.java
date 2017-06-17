package ctu.tranminhtai.ungdung;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Playgame extends AppCompatActivity {
    protected Button btnBauCua,btnRutLaBai, btnTraLoiCauHoi;
    protected RelativeLayout backGround;
    protected MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        backGround = (RelativeLayout)findViewById(R.id.activity_playgame);
        backGround.setBackgroundResource(R.drawable.background28);


        btnBauCua = (Button)findViewById(R.id.buttonBauCua);
        btnBauCua.setText("Game Bầu Cua");
        btnBauCua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chuyen = new Intent(Playgame.this,Baucua.class);
                startActivity(Chuyen);
            }
        });

        btnRutLaBai = (Button)findViewById(R.id.buttonRutLaBai);
        btnRutLaBai.setText("Game Rút Lá Bài");
        btnRutLaBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chuyen = new Intent(Playgame.this,Rutlabai.class);
                startActivity(Chuyen);
            }
        });

        btnTraLoiCauHoi = (Button)findViewById(R.id.buttonTraLoiCauHoi);
        btnTraLoiCauHoi.setText("Game Trả Lời Câu Hỏi");
        btnTraLoiCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chuyen = new Intent(Playgame.this,Traloicauhoi.class);
                startActivity(Chuyen);
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd=new AlertDialog.Builder(Playgame.this);
            bd.setTitle("Thông báo nhỏ!!");
            bd.setMessage("Bạn muốn thoát game??");
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

