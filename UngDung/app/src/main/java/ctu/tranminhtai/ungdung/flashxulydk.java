package ctu.tranminhtai.ungdung;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class flashxulydk extends AppCompatActivity {
    Handler hf;
    Bundle nhantienhieu;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashxulydk);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"hand.ttf");
        textView=(TextView)findViewById(R.id.txxuly);
        textView.setTypeface(typeface);
        //phan xu ly:
        nhantienhieu=getIntent().getExtras();
        final int kq=nhantienhieu.getInt("tn");
        final String tennv=nhantienhieu.getString("tendk");
        hf=new Handler();
        hf.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(kq==1)
                {
                    Intent xacnhan=new Intent(getApplication(),NhanDangKi.class);
                    xacnhan.putExtra("name",tennv);
                    startActivity(xacnhan);
                    finish();
                }
                else if(kq==0)
                {
                    Intent tuchoi=new Intent(getApplicationContext(),XacNhanDangKi.class);
                    startActivity(tuchoi);
                    finish();
                }
            }
        },2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flashxulydk, menu);
        return true;
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
