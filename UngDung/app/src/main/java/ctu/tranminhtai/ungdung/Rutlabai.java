package ctu.tranminhtai.ungdung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Rutlabai extends AppCompatActivity {
    protected RelativeLayout relativeLayout;
    protected ImageView imgLaBai11, imgLaBai12, imgLaBai13, imgLaBai21, imgLaBai22, imgLaBai23;
    protected Button btnPlay;
    protected TextView txtHuongDan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutlabai);
        AnhXa();
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rd = new Random();
                int n1,n2,n3,n4,n5,n6;
                n1 = rd.nextInt(52);
                int tam;
                tam = rd.nextInt(52);
                while(tam==n1){
                    tam = rd.nextInt(52);
                }
                n2 = tam;
                while((tam==n1 || (tam==n2))){
                    tam = rd.nextInt(52);
                }
                n3 = tam;
                while((tam==n1 || (tam==n2)|| (tam==n3))){
                    tam = rd.nextInt(52);
                }
                n4 = tam;
                while((tam==n1 || (tam==n2))|| (tam==n3)|| (tam==n4)){
                    tam = rd.nextInt(52);
                }
                n5 = tam;
                while((tam==n1 || (tam==n2)|| (tam==n3)|| (tam==n4)|| (tam==n5))){
                    tam = rd.nextInt(52);
                }
                n6 = tam;
                //imgLaBai11.setImageResource(R.drawable.ch1);
                Chon(n1,imgLaBai11);
                Chon(n2,imgLaBai12);
                Chon(n3,imgLaBai13);
                Chon(n4,imgLaBai21);
                Chon(n5,imgLaBai22);
                Chon(n6,imgLaBai23);
            }
        });
    }
    public void Chon(int n, ImageView img){
        switch (n){
            case 0:
                img.setImageResource(R.drawable.b1);break;
            case 1:
                img.setImageResource(R.drawable.b2);break;
            case 2:
                img.setImageResource(R.drawable.b3);break;
            case 3:
                img.setImageResource(R.drawable.b4);break;
            case 4:
                img.setImageResource(R.drawable.b5);break;
            case 5:
                img.setImageResource(R.drawable.b6);break;
            case 6:
                img.setImageResource(R.drawable.b7);break;
            case 7:
                img.setImageResource(R.drawable.b8);break;
            case 8:
                img.setImageResource(R.drawable.b9);break;
            case 9:
                img.setImageResource(R.drawable.b10);break;
            case 10:
                img.setImageResource(R.drawable.b11);break;
            case 11:
                img.setImageResource(R.drawable.b12);break;
            case 12:
                img.setImageResource(R.drawable.b13);break;
            case 13:
                img.setImageResource(R.drawable.c1);break;
            case 14:
                img.setImageResource(R.drawable.c2);break;
            case 15:
                img.setImageResource(R.drawable.c3);break;
            case 16:
                img.setImageResource(R.drawable.c4);break;
            case 17:
                img.setImageResource(R.drawable.c5);break;
            case 18:
                img.setImageResource(R.drawable.c6);break;
            case 19:
                img.setImageResource(R.drawable.c7);break;
            case 20:
                img.setImageResource(R.drawable.c8);break;
            case 21:
                img.setImageResource(R.drawable.c9);break;
            case 22:
                img.setImageResource(R.drawable.c10);break;
            case 23:
                img.setImageResource(R.drawable.c11);break;
            case 24:
                img.setImageResource(R.drawable.c12);break;
            case 25:
                img.setImageResource(R.drawable.c13);break;
            case 26:
                img.setImageResource(R.drawable.r1);break;
            case 27:
                img.setImageResource(R.drawable.r2);break;
            case 28:
                img.setImageResource(R.drawable.r3);break;
            case 29:
                img.setImageResource(R.drawable.r4);break;
            case 30:
                img.setImageResource(R.drawable.r5);break;
            case 31:
                img.setImageResource(R.drawable.r6);break;
            case 32:
                img.setImageResource(R.drawable.r7);break;
            case 33:
                img.setImageResource(R.drawable.r8);break;
            case 34:
                img.setImageResource(R.drawable.r9);break;
            case 35:
                img.setImageResource(R.drawable.r10);break;
            case 36:
                img.setImageResource(R.drawable.r11);break;
            case 37:
                img.setImageResource(R.drawable.r12);break;
            case 38:
                img.setImageResource(R.drawable.r13);break;
            case 39:
                img.setImageResource(R.drawable.ch1);break;
            case 40:
                img.setImageResource(R.drawable.ch2);break;
            case 41:
                img.setImageResource(R.drawable.ch3);break;
            case 42:
                img.setImageResource(R.drawable.ch4);break;
            case 43:
                img.setImageResource(R.drawable.ch5);break;
            case 44:
                img.setImageResource(R.drawable.ch6);break;
            case 45:
                img.setImageResource(R.drawable.ch7);break;
            case 46:
                img.setImageResource(R.drawable.ch8);break;
            case 47:
                img.setImageResource(R.drawable.ch9);break;
            case 48:
                img.setImageResource(R.drawable.ch10);break;
            case 49:
                img.setImageResource(R.drawable.ch11);break;
            case 50:
                img.setImageResource(R.drawable.ch12);break;
            case 51:
                img.setImageResource(R.drawable.ch13);break;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd = new AlertDialog.Builder(Rutlabai.this);
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
    public void AnhXa(){
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_rutlabai);
        relativeLayout.setBackgroundResource(R.drawable.bdrutlabai);
        imgLaBai11 = (ImageView) findViewById(R.id.imageViewLaBai11);
        imgLaBai11.setImageResource(R.drawable.labaidoup);

        imgLaBai12 = (ImageView) findViewById(R.id.imageViewLaBai12);
        imgLaBai12.setImageResource(R.drawable.labaidoup);

        imgLaBai13 = (ImageView) findViewById(R.id.imageViewLaBai13);
        imgLaBai13.setImageResource(R.drawable.labaidoup);

        imgLaBai21 = (ImageView) findViewById(R.id.imageViewLaBai21);
        imgLaBai21.setImageResource(R.drawable.labaiupxanh);

        imgLaBai22 = (ImageView) findViewById(R.id.imageViewLaBai22);
        imgLaBai22.setImageResource(R.drawable.labaiupxanh);

        imgLaBai23 = (ImageView) findViewById(R.id.imageViewLaBai23);
        imgLaBai23.setImageResource(R.drawable.labaiupxanh);

        txtHuongDan = (TextView) findViewById(R.id.textViewHuongDan);
        txtHuongDan.setText("Hướng Dẫn");
        txtHuongDan.setTextColor(Color.RED);
        txtHuongDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getApplicationContext(),
                        "Mỗi người có 3 lá bài nhẫu nhiên không giống nhau, bạn sẽ tự tính điểm với quy luật của bài 3 lá",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
