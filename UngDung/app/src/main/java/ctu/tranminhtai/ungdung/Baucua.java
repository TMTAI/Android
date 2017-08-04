package ctu.tranminhtai.ungdung;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Baucua extends AppCompatActivity {
    protected Button btnLac;
    protected ImageView img1, img2, img3;
    protected int tiencu, tienmoi, diem, ktr, kh;
    protected int gtxn1,gtxn2,gtxn3;
    protected ImageView tom,cua,bau,ga,ca,nai;
    protected TextView txtView,  txtBanChon, txtDiem;
    public static Integer gt[]=new Integer[6];
    protected GridView gridView;
    protected Handler hd;
    protected MediaPlayer mpl;
    protected Timer tm=new Timer();
    protected AnimationDrawable cdxn1,cdxn2,cdxn3;
    customRelativie customRelativie;
    Integer []ds={R.drawable.ga,R.drawable.tom,R.drawable.ca,R.drawable.nai,R.drawable.cua,R.drawable.bau};
    Random n1,n2,n3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        diem = 800;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baucua);
        mpl = MediaPlayer.create(getApplication(),R.raw.xingau);
        AnhXa();
        sharedPreferences = getApplicationContext().getSharedPreferences("diemtong", Context.MODE_PRIVATE);
        txtDiem.setText(Integer.toString(sharedPreferences.getInt("Tongtien",diem)));
        final Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textdown);
        txtDiem.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in));
        SetFont();
        btnLac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lacxingau();
            }
        });
        Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Raxn1();
                Raxn2();
                Raxn3();
                for(int i=0;i<gt.length;i++)
                {
                    ktr+=gt[i];
                    if(gt[i]!=0)
                    {
                        if(i==gtxn1)
                        {
                            diem+=gt[i];
                            txtDiem.setText(Integer.toString(diem));
                            txtDiem.startAnimation(animation1);
                            toast3("Xí ngầu 1 cộng: " + gt[i]);
                        }
                        if(i == gtxn2)
                        {
                            diem+=gt[i];
                            txtDiem.setText(Integer.toString(diem));
                            txtDiem.startAnimation(animation1);
                            toast1("Xí ngầu 2 cộng: "+gt[i]);
                        }
                        if(i == gtxn3)
                        {
                            diem+=gt[i];
                            txtDiem.setText(Integer.toString(diem));
                            txtDiem.startAnimation(animation1);
                            toast2("Xí ngầu 3 cộng: " + gt[i]);
                        }
                        if(i != gtxn1&&i!=gtxn2&&i!=gtxn3)
                        {
                            diem-=gt[i];
                            toast4("Ẹc! trừ bạn: "+gt[i]);
                            txtDiem.setText(Integer.toString(diem));

                        }
                    }
                }
                Log.d("kq","lac:"+gtxn1+"-"+gtxn2+"-"+gtxn3);
                //LUU:
                luuTru(diem);
                return false;
            }
        };
        hd=new Handler(callback);
    }
    public void AnhXa(){
        gridView=(GridView)findViewById(R.id.gib);
        customRelativie = new customRelativie(this,R.layout.custombanco,ds);
        gridView.setAdapter(customRelativie);

        img1 =(ImageView)findViewById(R.id.imageView1);
        img2 =(ImageView)findViewById(R.id.ImageView);
        img3 =(ImageView)findViewById(R.id.imageView3);

        btnLac =(Button)findViewById(R.id.buttonLac);
        txtDiem =(TextView)findViewById(R.id.textViewDiem);
        txtView = (TextView)findViewById(R.id.textViewNoiDung);
        txtBanChon = (TextView)findViewById(R.id.textViewNoiDung);
    }
    public void SetFont(){
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"tienvit.ttf");
        txtDiem.setTypeface(typeface1);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"hand.ttf");
        btnLac.setTypeface(typeface);
        Typeface Ttypeface = Typeface.createFromAsset(getAssets(),"fontt.ttf");
    }
    public  void Raxn1()
    {
        n1=new Random();
        int r = n1.nextInt(6);
        switch (r)
        {
            case 0:
                img1.setImageResource(ds[0]);
                gtxn1=r;
                break;
            case 1:
                img1.setImageResource(ds[1]);
                gtxn1=r;
                break;
            case 2:
                img1.setImageResource(ds[2]);
                gtxn1=r;
                break;
            case 3:
                img1.setImageResource(ds[3]);
                gtxn1=r;
                break;
            case 4:
                img1.setImageResource(ds[4]);
                gtxn1=r;
                break;
            case 5:
                img1.setImageResource(ds[5]);
                gtxn1=r;
                break;

        }
    }

    public  void Raxn2()
    {
        n2=new Random();
        int r=n2.nextInt(6);
        switch (r)
        {
            case 0:
                img2.setImageResource(ds[0]);
                gtxn2=r;
                break;
            case 1:
                img2.setImageResource(ds[1]);
                gtxn2=r;
                break;
            case 2:
                img2.setImageResource(ds[2]);
                gtxn2=r;
                break;
            case 3:
                img2.setImageResource(ds[3]);
                gtxn2=r;
                break;
            case 4:
                img2.setImageResource(ds[4]);
                gtxn2=r;
                break;
            case 5:
                img2.setImageResource(ds[5]);
                gtxn2=r;
                break;

        }
    }
    public  void Raxn3()
    {
        n3=new Random();
        int r=n3.nextInt(6);
        switch (r)
        {
            case 0:
                img3.setImageResource(ds[0]);
                gtxn3=r;
                break;
            case 1:
                img3.setImageResource(ds[1]);
                gtxn3=r;
                break;
            case 2:
                img3.setImageResource(ds[2]);
                gtxn3=r;
                break;
            case 3:
                img3.setImageResource(ds[3]);
                gtxn3=r;
                break;
            case 4:
                img3.setImageResource(ds[4]);
                gtxn3=r;
                break;
            case 5:
                img3.setImageResource(ds[5]);
                gtxn3=r;
                break;
        }
    }
    public void luuTru(int tien){
        editor = sharedPreferences.edit();
        editor.putInt("Tongtien",tien);
        editor.commit();
    }
    public void toast1(String str)
    {
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        View v=t.getView();
        v.setBackgroundColor(Color.GREEN);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    public void toast4(String str)
    {
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        View v=t.getView();
        v.setBackgroundColor(Color.GREEN);
        t.setGravity(Gravity.BOTTOM,0,0);
        t.show();
    }
    public void toast2(String str)
    {
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        View v=t.getView();
        v.setBackgroundColor(Color.YELLOW);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    public void toast3(String str)
    {
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        View v=t.getView();
        v.setBackgroundColor(Color.BLUE);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    public void setDiem(){
        SharedPreferences.Editor editor1=sharedPreferences.edit();
        diem = 800;
        editor1.putInt("Tongtien",diem);
        editor1.commit();
        txtDiem.setText(String.valueOf(sharedPreferences.getInt("Tongdiem",diem)));

    }
    public  void lacxingau()
    {
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textin);
        for (int i = 0; i < gt.length; i++) {
            Log.d("vitri", "vi tri:" + i + "," + "--" + gt[i]);
            kh+=gt[i];
            if(i==0&&gt[i]!=0)
            {
                thongbao("gà:" + gt[i]);
                txtBanChon.setText( "gà: " +gt[i]+ " ");
                txtBanChon.startAnimation(animation);

            }
            if(i==1&&gt[i]!=0)
            {
                thongbao("tôm:" + gt[i]);
                txtBanChon.setText(txtBanChon.getText()+"tôm: "+gt[i]+" ");
                txtBanChon.startAnimation(animation);

            }
            if(i==2&&gt[i]!=0)
            {
                thongbao("cá:" + gt[i]);
                txtBanChon.setText(txtBanChon.getText()+"cá: "+gt[i]+" ");
                txtBanChon.startAnimation(animation);

            }
            if(i==3&&gt[i]!=0)
            {
                thongbao("nai:" + gt[i]);
                txtBanChon.setText(txtBanChon.getText()+"nai: "+gt[i]+" ");
                txtBanChon.startAnimation(animation);

            }
            if(i==4&&gt[i]!=0)
            {
                thongbao("cua:" + gt[i]);
                txtBanChon.setText(txtBanChon.getText()+"cua: "+gt[i]+" ");
                txtBanChon.startAnimation(animation);

            }
            if(i==5&&gt[i]!=0)
            {
                thongbao("bầu:" +gt[i]);
                txtBanChon.setText(txtBanChon.getText() + "bầu: " +gt[i]+ " ");
                txtBanChon.startAnimation(animation);
            }
        }
        if(kh==0) {
            Toast t = Toast.makeText(getApplicationContext(), "Chọn phần dưới chân hình để đặt cược!!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 0);
            t.show();
        }
        else {
            if (sharedPreferences.getInt("Tongtien",diem) <=0) {
                AlertDialog.Builder bd = new AlertDialog.Builder(Baucua.this);
                bd.setTitle("Thông báo");
                bd.setMessage("Diểm dưới 0.không dủ đặt cược!Bạn có muốn chơi lại");
                bd.setPositiveButton("Chơi lại",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setDiem();
                    }
                });
                bd.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int which) {
                        finish();
                    }
                });
                AlertDialog al = bd.create();
                al.show();
            } else {
                if (mpl.isPlaying()) {
                    mpl.stop();
                    mpl.release();
                } else {
                    mpl.start();
                }
                img1.setImageResource(R.drawable.lac);
                img2.setImageResource(R.drawable.lac);
                img3.setImageResource(R.drawable.lac);
                cdxn1 = (AnimationDrawable) img1.getDrawable();
                cdxn2 = (AnimationDrawable) img2.getDrawable();
                cdxn3 = (AnimationDrawable) img3.getDrawable();
                cdxn1.start();
                cdxn2.start();
                cdxn3.start();
                tm.schedule(new lacxn(), 1500);
            }
        }
    }
    public void thongbao(String str)
    {
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd=new AlertDialog.Builder(Baucua.this);
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
    class lacxn extends TimerTask {
        @Override
        public void run() {
            hd.sendEmptyMessage(0);
        }
    }

}
