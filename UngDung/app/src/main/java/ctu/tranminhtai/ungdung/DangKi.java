package ctu.tranminhtai.ungdung;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKi extends AppCompatActivity {
    Button xn;
    EditText exn;
    TextView txchuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        //anh xa hinh nen:
        xn=(Button)findViewById(R.id.btxn);
        exn=(EditText)findViewById(R.id.ed1);
        txchuy=(TextView)findViewById(R.id.marque_scrolling_text);
        txchuy.setSelected(true);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"GeorgiaBold.ttf");
        xn.setTypeface(typeface);

        //tao nut:
        xn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tr=exn.getText().toString();
                //khai bao chuoi:
                String kytu=tr;
                Pattern pt1= Pattern.compile("^[^#$@%&*^%`~?:()]\\D[^*$!&%#@]*");
                Matcher kc=pt1.matcher(kytu);
                Intent gui = new Intent(getApplicationContext(), flashxulydk.class);
                if(exn.getText().length()!=0) {
                    if(kc.matches())
                    {
                        //Intent guikq = new Intent(dk.this, nhandk.class);
                        gui.putExtra("tendk",tr);
                        gui.putExtra("tn",1);
                        startActivity(gui);
                        finish();
                    }
                    else
                    {
                        //Intent loi=new Intent(dk.this,xndk.class);

                        gui.putExtra("tn",0);
                        startActivity(gui);
                        finish();
                    }
                }
                else
                {
                    //Intent loi=new Intent(dk.this,xndk.class);
                    gui.putExtra("tn",0);
                    startActivity(gui);
                    finish();
                }

            }
        });
    }
    //bat su kien nut back ket thuc nhac:
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd = new AlertDialog.Builder(getApplicationContext());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dk, menu);
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
