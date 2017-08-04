package ctu.tranminhtai.ungdung;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class NhanDangKi extends AppCompatActivity {
    Button nhan;
    TextView getnhan;
    MediaPlayer xn;
    TextView textView;
    android.os.Handler handler = new android.os.Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_dang_ki);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"bbbt.ttf");
        //Anh xa:
        nhan=(Button)findViewById(R.id.btndk);
        getnhan=(TextView)findViewById(R.id.tndk);
        textView=(TextView)findViewById(R.id.tndk);
        textView.setTypeface(typeface);
        //tao media:
        xn=MediaPlayer.create(getApplicationContext(),R.raw.yeah);
        Typeface typeface1=Typeface.createFromAsset(getAssets(),"GeorgiaBold.ttf");
        nhan.setTypeface(typeface1);
        xn.start();
        //khai bao bunder:
        Bundle nv = getIntent().getExtras();
        if(nv != null)
        {
            String lay = nv.getString("name");
            getnhan.setText("Chúc mừng:" + lay + ",đã xác nhận thành công!!");
            HieuUng();
        }
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xn.stop();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent back = new Intent(getApplicationContext(),MainActivity.class);
                        Bundle n = getIntent().getExtras();
                        String nhanve = n.getString("name");
                        back.putExtra("trave",nhanve.toString());
                        startActivity(back);
                        Intent tv = new Intent(getApplicationContext(),DangKi.class);
                        tv.putExtra("trave","mot");
                        finish();
                        overridePendingTransition(R.anim.fadein,0);
                    }
                },500);
            }
        });
    }

    //Tao hieu ung chu zoom:
    public  void HieuUng()
    {
        Animation z= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textpink);
        z.reset();
        getnhan.clearAnimation();
        getnhan.startAnimation(z);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nhandk, menu);
        return true;
    }
    //nut back ve:
    //bat su kien nut back ket thuc nhac:
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            xn.stop();
            AlertDialog.Builder bd=new AlertDialog.Builder(getApplicationContext());
            bd.setTitle("Thông báo nhỏ!!");
            bd.setMessage("Bạn muốn thoát!");
            //tao button ok:
            AlertDialog.Builder builder = bd.setPositiveButton("Ô kê", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    finish();
                }
            });
            bd.setNegativeButton("Thôi",new DialogInterface.OnClickListener() {
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
