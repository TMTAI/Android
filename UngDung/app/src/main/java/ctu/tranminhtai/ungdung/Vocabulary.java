package ctu.tranminhtai.ungdung;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;


public class Vocabulary extends AppCompatActivity {
    private Handler handler =  new Handler();
    private TextView txtWord, txtNghia, txtType, txtPhatAm;
    private MediaPlayer mediaPlayer;
    private ImageButton imgbSpeak, imgbBack;
    private Bundle bundle = new Bundle();
    private String nhave;
    private int[] danhsach ={
                R.raw.ability,R.raw.able,R.raw.about,R.raw.academy,R.raw.abst,R.raw.account,
                R.raw.active,R.raw.actual,R.raw.advance,R.raw.advice,R.raw.above,R.raw.act,R.raw.add,R.raw.afraid,
                R.raw.after,R.raw.again,R.raw.age,R.raw.ago,R.raw.agree,R.raw.air,R.raw.all,R.raw.allow,R.raw.also,
                R.raw.always,R.raw.among,R.raw.anger,R.raw.animal,R.raw.answer,R.raw.any,R.raw.appear,R.raw.area,R.raw.arrange,
                R.raw.arrive,R.raw.art,R.raw.ask,R.raw.atom,R.raw.baby,R.raw.back,R.raw.bad,R.raw.ball,R.raw.band,R.raw.bank,
                R.raw.bar,R.raw.base,R.raw.basic,R.raw.bat,R.raw.be,R.raw.bear,R.raw.beat,R.raw.beauty,R.raw.bed,
                R.raw.before,R.raw.begin,R.raw.behind,R.raw.believe,R.raw.bell,R.raw.best,R.raw.better,R.raw.between,
                R.raw.big,R.raw.bird,R.raw.bit,R.raw.black,R.raw.block,R.raw.blood,R.raw.blow,R.raw.blue,R.raw.board,
                R.raw.boat,R.raw.body,R.raw.bone,R.raw.book,R.raw.born,R.raw.both,R.raw.bottom,R.raw.buy,R.raw.box,R.raw.boy,
                R.raw.branch,R.raw.bread,R.raw.br,R.raw.bright,R.raw.bring,R.raw.broad,R.raw.broke,R.raw.brother,
                R.raw.brown,R.raw.build,R.raw.burn,R.raw.busy,R.raw.by,R.raw.call,R.raw.came,R.raw.camp,R.raw.can,R.raw.capital,R.raw.captain,
                R.raw.car,R.raw.card,R.raw.care,R.raw.carry,R.raw.cs,R.raw.cat,R.raw.ch,R.raw.cause,R.raw.cell,R.raw.cent,
                R.raw.center,R.raw.century,R.raw.certain,R.raw.chair,R.raw.chance,R.raw.change,R.raw.character,R.raw.charge,R.raw.chart,
                R.raw.check,R.raw.chick,R.raw.chief,R.raw.child,R.raw.choose,R.raw.chord,R.raw.circle,R.raw.city,R.raw.claim,R.raw.cl,
                R.raw.clean,R.raw.clear,R.raw.climb,R.raw.clock,R.raw.close,R.raw.clothe,R.raw.cloud,R.raw.coast,R.raw.coat,R.raw.cold,R.raw.collect,
                R.raw.colony,R.raw.color,R.raw.column,R.raw.come,R.raw.common,R.raw.company,R.raw.compare,R.raw.complete,R.raw.condition,R.raw.connect,
                R.raw.consider,R.raw.consonant,R.raw.contain,R.raw.continent};;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        AnhXa();
        SetFont();

        //Khai bao hieu ung:
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_in_left);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textin);

        //Khai bao mang danh sach Audio tu vug:
        //lay gia tri ve:
        bundle = getIntent().getExtras();
        if (bundle != null){
            nhave = bundle.getString("Bạn Chọn");
        }
        if(!"".equals(nhave)){
            txtWord.setText(nhave);
            txtWord.startAnimation(animation);
            //Mo csdl ra:
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase database = (SQLiteDatabase)dbHelper.openDataBase();
            String sql = "Select * from dictionary where word = '"+nhave+"'";
            final Cursor kq = database.rawQuery(sql,null);
            kq.moveToFirst();
            //dua giatri vao:
            txtNghia.setText(kq.getString(3).toString());
            txtNghia.setMovementMethod(new ScrollingMovementMethod());
            txtNghia.startAnimation(animation1);
            txtType.setText(kq.getString(2).toString());
            txtType.startAnimation(animation1);
            txtPhatAm.setText(kq.getString(4).toString());
            txtPhatAm.startAnimation(animation1);
            //Khai bao Su kien click:
            mediaPlayer = null;
            imgbSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(danhsach.length >= kq.getInt(0)) {
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), danhsach[(kq.getInt(0)) - 1]);
                        mediaPlayer.start();
                    }

                }
            });
        }
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },500);
            }
        });

    }
    public void AnhXa(){
        txtNghia = (TextView)findViewById(R.id.TextViewNghia);
        txtWord = (TextView)findViewById(R.id.TextViewWord);
        txtType = (TextView)findViewById(R.id.TextViewType);
        txtPhatAm = (TextView)findViewById(R.id.TextViewPhatAm);

        imgbBack = (ImageButton)findViewById(R.id.ImageButtonGobackContent);
        imgbSpeak = (ImageButton)findViewById(R.id.ImageButtonSpeak);
    }
    public void SetFont(){
        Typeface t1=Typeface.createFromAsset(getAssets(),"fontt.ttf");
        txtNghia.setTypeface(t1);
        txtWord.setTypeface(t1);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
        return super.onKeyDown(keyCode, event);
    }
}
