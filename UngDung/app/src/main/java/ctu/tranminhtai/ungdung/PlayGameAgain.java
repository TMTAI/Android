package ctu.tranminhtai.ungdung;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayGameAgain extends AppCompatActivity {

    Button ql;
    Button trv;
    Button thoatve;
    TextView diemfi;
    Bundle bd;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_again);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"GeorgiaBold.ttf");
        Typeface typeface1=Typeface.createFromAsset(getAssets(),"fontt.ttf");
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.mario);
        mediaPlayer.start();
        //anh xa:
        trv=(Button)findViewById(R.id.buttonlamlai);
        trv.setTypeface(typeface);
        thoatve=(Button)findViewById(R.id.btgov);
        thoatve.setTypeface(typeface);
        //
        trv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                Intent back=new Intent(getApplicationContext(),Traloicauhoi.class);
                startActivity(back);
                finish();
                overridePendingTransition(R.anim.fadein,0);
            }
        });
        thoatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                finish();
            }
        });
    }


    //bat su kien nut back ket thuc nhac:
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            mediaPlayer.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playgameagain, menu);
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
