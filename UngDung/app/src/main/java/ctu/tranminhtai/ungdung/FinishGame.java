package ctu.tranminhtai.ungdung;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishGame extends AppCompatActivity {

    protected TextView boxanhxadiem;
    protected Button btnTroVe;
    protected TextView txtDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        Bundle nhanve = getIntent().getExtras();
        int diem = nhanve.getInt("diem");
        btnTroVe = (Button)findViewById(R.id.buttontrove);
        txtDiem = (TextView)findViewById(R.id.txdiem);
        //
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fontt.ttf");
        Typeface ty=Typeface.createFromAsset(getAssets(),"GeorgiaBold.ttf");
        btnTroVe.setTypeface(ty);
        txtDiem.setTypeface(typeface);
        txtDiem.setText(String.valueOf(diem));
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finishgame, menu);
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
