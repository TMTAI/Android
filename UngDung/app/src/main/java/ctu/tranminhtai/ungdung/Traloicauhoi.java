package ctu.tranminhtai.ungdung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class Traloicauhoi extends AppCompatActivity {
    TextView txtSoDiem,txcauhoi, txtSoLuotChoi, txluotchoi, txtSoCauConLai;
    Button btnA, btnB, btnC, btnD;
    int dem=4, da=0,diem=0, soluongcauhoi;
    Vector cauhoi, row;
    Random ra = new Random(), r;
    String dp;
    Handler handler = new Handler();
    MediaPlayer mediaPlayer, mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traloicauhoi);
        //Anh xa len:
        AnhXa();
        //set font:
        SetFont();
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textdown);
        final Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textdown);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.flappycut);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.flappytt);
        CauHoi();
        //tao bien radom:
        soluongcauhoi = cauhoi.size();
        txtSoDiem.setText( Integer.toString(diem));
        txtSoDiem.startAnimation(animation);
        txtSoLuotChoi.setText(Integer.toString(dem));
        txtSoLuotChoi.startAnimation(animation);
        setanswer((Vector) cauhoi.get(ra.nextInt(cauhoi.size())));
        txtSoCauConLai.setText(Integer.toString(soluongcauhoi));

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tl=1;
                if(tl==da){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast(dp);
                            if(!btnA.isClickable())
                            {
                                mp.stop();
                                mp.release();
                            }
                            else
                            {
                                mp.start();
                            }
                            mp.start();
                            if(!cauhoi.contains(ra.nextInt(cauhoi.size()))) {
                                setanswer((Vector) cauhoi.remove(ra.nextInt(cauhoi.size())));
                                diem +=10;
                                txtSoDiem.startAnimation(animation1);
                                txtSoDiem.setText(Integer.toString(diem));
                                txtSoCauConLai.setText(Integer.toString(soluongcauhoi));
                            }
                        }
                    },800);
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!btnA.isClickable()) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                            }
                            else {
                                mediaPlayer.start();
                            }
                            toast();
                            dem-=1;
                            txtSoLuotChoi.startAnimation(animation);
                            txtSoLuotChoi.setText(Integer.toString(dem));
                        }
                    },800);
                }
                if(soluongcauhoi <=1 ){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent chuyentoi=new Intent(getApplicationContext(),FinishGame.class);
                            chuyentoi.putExtra("diem",diem);
                            startActivity(chuyentoi);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
                if((dem == 0))
                {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mhtach = new Intent(getApplicationContext(),Thuagame.class);
                            startActivity(mhtach);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
            }
        });
        //
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tl=2;
                if(tl==da){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast(dp);
                            if (!btnB.isClickable()) {
                                mp.stop();
                                mp.release();
                            } else {
                                mp.start();
                            }
                            mp.start();
                            if (!cauhoi.contains(ra.nextInt(cauhoi.size()))) {
                                setanswer((Vector) cauhoi.remove(ra.nextInt(cauhoi.size())));
                                diem += 20;
                                txtSoDiem.setText(Integer.toString(diem));
                                txtSoDiem.startAnimation(animation1);
                                txtSoCauConLai.setText(Integer.toString(soluongcauhoi));
                            }
                        }
                    }, 800);
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!btnB.isClickable()) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                            }
                            else {
                                mediaPlayer.start();
                            }
                            toast();
                            dem -= 1;
                            txtSoLuotChoi.setText(Integer.toString(dem));
                            txtSoLuotChoi.startAnimation(animation);
                        }
                    }, 800);
                }
                if(soluongcauhoi <=1 ){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent chuyentoi = new Intent(getApplicationContext(),FinishGame.class);
                            chuyentoi.putExtra("diem",diem);
                            startActivity(chuyentoi);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
                if(dem == 0)
                {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mhtach=new Intent(getApplicationContext(),Thuagame.class);
                            startActivity(mhtach);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
            }
        });
        //
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tl=3;
                if(tl==da){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast(dp);
                            if (!btnC.isClickable()) {
                                mp.stop();
                                mp.release();
                            } else {
                                mp.start();
                            }
                            mp.start();
                            if (!cauhoi.contains(ra.nextInt(cauhoi.size()))) {
                                setanswer((Vector) cauhoi.remove(ra.nextInt(cauhoi.size())));
                                diem += 30;
                                txtSoDiem.setText(Integer.toString(diem));
                                txtSoDiem.startAnimation(animation1);
                                txtSoCauConLai.setText(Integer.toString(soluongcauhoi));
                            }
                        }
                    }, 800);
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!btnC.isClickable()) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                            }
                            else {
                                mediaPlayer.start();
                            }
                            toast();
                            dem -= 1;
                            txtSoLuotChoi.setText(Integer.toString(dem));
                            txtSoLuotChoi.startAnimation(animation);
                        }
                    }, 800);
                }
                if(soluongcauhoi <=1 ){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent chuyentoi=new Intent(getApplicationContext(),FinishGame.class);
                            chuyentoi.putExtra("diem",diem);
                            startActivity(chuyentoi);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
                if(dem == 0)
                {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mhtach=new Intent(getApplicationContext(),Thuagame.class);
                            startActivity(mhtach);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tl=4;
                if(tl==da){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast(dp);
                            if (!btnD.isClickable()) {
                                mp.stop();
                                mp.release();
                            } else {
                                mp.start();
                            }
                            mp.start();
                            if (!cauhoi.contains(ra.nextInt(cauhoi.size()))) {
                                setanswer((Vector) cauhoi.remove(ra.nextInt(cauhoi.size())));
                                diem += 40;
                                txtSoDiem.setText(Integer.toString(diem));
                                txtSoDiem.startAnimation(animation1);
                                txtSoCauConLai.setText(Integer.toString(soluongcauhoi));
                            }
                        }
                    }, 800);
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!btnA.isClickable()) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                            }
                            else {
                                mediaPlayer.start();
                            }
                            toast();
                            dem -= 1;
                            txtSoLuotChoi.setText(Integer.toString(dem));
                            txtSoLuotChoi.startAnimation(animation);
                        }
                    }, 800);
                }
                if(soluongcauhoi <=1 ){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent chuyentoi=new Intent(getApplicationContext(),FinishGame.class);
                            chuyentoi.putExtra("diem",diem);
                            startActivity(chuyentoi);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
                if(dem == 0)
                {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mhGameOver = new Intent(getApplicationContext(), Thuagame.class);
                            startActivity(mhGameOver);
                            finish();
                            overridePendingTransition(R.anim.fadein,0);
                        }
                    },1500);
                }
            }
        });
    };
    public void AnhXa(){
        txtSoDiem = (TextView) findViewById(R.id.TextViewSoDiem);
        txcauhoi = (TextView) findViewById(R.id.txnoidungcauhoi);
        txtSoCauConLai = (TextView) findViewById(R.id.textViewSoCauConLai);
        btnA = (Button) findViewById(R.id.ButtonD);
        btnB = (Button) findViewById(R.id.ButtonC);
        btnC = (Button) findViewById(R.id.ButtonB);
        btnD = (Button) findViewById(R.id.ButtonA);
        txtSoLuotChoi=(TextView)findViewById(R.id.TextViewSoLuotChoi);
        txluotchoi=(TextView)findViewById(R.id.TextViewLuotChoi);
        txcauhoi.setMovementMethod(new ScrollingMovementMethod());
        txcauhoi.startAnimation(AnimationUtils.loadAnimation(getApplication(),android.R.anim.slide_out_right));
    }
    public void SetFont(){
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fontt.ttf");
        txcauhoi.setTypeface(typeface);
        Typeface ty=Typeface.createFromAsset(getAssets(),"GeorgiaBold.ttf");
        btnA.setTypeface(ty);
        btnB.setTypeface(ty);
        btnC.setTypeface(ty);
        btnD.setTypeface(ty);
    }
    public void toast()
    {
        Toast t=Toast.makeText(getApplicationContext(),"Tạch rồi haha!",Toast.LENGTH_SHORT);
        View v=t.getView();
        v.setBackgroundColor(Color.MAGENTA);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    //ham Toast:
    public void toast(String tr)
    {
        Typeface t=Typeface.createFromAsset(getAssets(),"hand.ttf");
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_custom_toast,
                (ViewGroup) findViewById(R.id.root));
        layout.setBackgroundColor(Color.GREEN);
        ImageView image = (ImageView) layout.findViewById(R.id.imagehinhtroll);
        image.setImageResource(R.drawable.vo);
        TextView text = (TextView) layout.findViewById(R.id.txcontent);
        text.setText(tr);
        text.setTypeface(t);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public void CauHoi(){
        cauhoi= new Vector();
        row = new Vector();
        themCauHoi(new String[]{"Bọn này càng lớn càng...", "cay cú","Éo quan tâm" ,"Next đuê", "già tuổi"}, 4,"lớn thì già chứ sao.");
        themCauHoi(new String[]{"Một điều nhịn chín điều..", "lành ","nhục" ,"nhịn", "không biết"}   , 1,"Quá dễ!");
        themCauHoi(new String[]{"Quần rộng nhất là quần gì.", "quần đảo","quần size XXl" ,"quần rộng là nó rộng", "xin thua"}   , 1,"Đúng rồi.");
        themCauHoi(new String[]{"Có cái thằng đọc báo trong đêm mặc dù điện tắc đèn tối thui không thấy gì cả,hỏi thằng đó có bị điên hok??", "chắc điên rồi","tại nó là dị nhân" ,"đáp án khác", "đọc bằng điện thoai mà"},4,"Còn trật đi đâu nửa.");
        themCauHoi(new String[]{"Tháng nào nhỏ nhất trong các tháng?", "tháng 10","tháng 4" ,"tháng 8", "tháng 1"}, 4,"Số 1 là nhỏ nhất àk");
        themCauHoi(new String[]{"Con rắn nằm ngang đường nó nhiêu tuổi vậy?", "69 tuổi","1800 tuổi" ,"1005 tuổi", "96 tuổi"}, 3,"nằm ngang ==> ngàn năm...keke");
        themCauHoi(new String[]{"Ba thằng què đi trước một thằng què hỏi có mấy thằng què.", "1 thằng","4 thằng" ,"không biết àk", "2 thằng"}, 1,"Tại ông đó là cha của thằng què.");
        themCauHoi(new String[]{"Lịch nào dài nhất trong năm.", "lịch sử","lịch vạn thiên niên kỷ kỷ" ,"lịch cổ xua", "lịch treo tường"}, 1,"Thiệt là dễ hết sức!");
        themCauHoi(new String[]{"Cơ quan quan trọng nhất của phụ nữ là gì ta??", "hihi","hok lẽ cái.." ,"Hội liên hiệp phụ nữ", "bậy bạ hết sức"}, 3,"Tưởng nghĩ bậy chớ.");
        themCauHoi(new String[]{"Có một câu hỏi mà bạn có thể hỏi mọi người suốt cả ngày.Mỗi lần hỏi bạn lại nhận được câu trả loài khác nhau nhưng câu trả lời nào cũng đúng?", "mai học môn gì","đang làm gì dó" ,"ăn cơm chưa", "mấy giờ rồi"},4,"Perfect!");
        themCauHoi(new String[]{"Trong các con vật sau đây con vậy nào khác biệt so với con vật còn lại?", "trâu ","cua" ,"hổ", "ba ba"}, 4,"ba ba có 2 từ nhiều hơn keke.");
        themCauHoi(new String[]{"Cái gì đánh cha, đánh má, đánh anh, đánh chị, đánh em?", "bàn chảy đáng răng","cây roi chứ gì" ,"có cái đó à", "thua"},1,"Đánh răng hằng ngày mà hé.");
        themCauHoi(new String[]{"Con gì càng to càng nhỏ.", "làm gì có con đó","con cá đem phơi khô teo lại" ,"cua", "bọ cạp"}, 3,"Hoan hô hô.");
        themCauHoi(new String[]{"Khi Beckham thực hiện quả đá phạt đền, anh ta sẽ sút vào đâu?", "chắc có đáp án # ","khung thành" ,"thủ môn", "ai biết đâu"}, 1,"đáp án khác nha( ổng sút vào trái banh.)");
        themCauHoi(new String[]{"Mẹ vợ của anh rễ bạn kêu bằng gì?", "Dì oy ","dạ thưa Bác" ,"mẹ", "đáp án khác"}, 3,"Cũng vẫn là mẹ thoy.");
        themCauHoi(new String[]{"Ai cũng thừa biết đỉnh núi Everest là đỉnh núi cao nhất thế giới vậy hiện tại bây giờ núi nào cao hơn Everest?", "Hymalaya","Phú sĩ" ,"Ngũ hành sơn Tây du kí", "Evrerest"}, 4,"Thông minh qué.");
        themCauHoi(new String[]{"Nước nào trên thế giới chẳng bao giờ dùng ly?", "Singapore","Ai cập" ,"Nữ nhi quốc", "Chi-Lê"}, 4,"Chi lê ==> Chê ly");
        themCauHoi(new String[]{"Một giọt nước lại thêm một giọt nước hỏi có mấy giọt?", "2","4-2-1 giọt" ,"5", "6-1 giọt"}, 2,"Chính xác.");
        themCauHoi(new String[]{"Từ gì mà 100% dân Việt Nam phát âm là sai?", "rung rinh","không sai" ,"đúng", "sai"}, 4,"Hay ghê.");
        themCauHoi(new String[]{"Làm sao để cái cân đồng hồ nó tự cân chính nó?", "lật ngược nó lại","đem nó lên cái cân khác" ,"không có đáp án", "âm bờ lí vơ bồ"},1,"Nó đó.");
        themCauHoi(new String[]{"Cái gì có thể giết chết một nhà vua,làm núi đá cũng phải mòn?", "Thời gian","chắc là ngu vì gái đẹp" ,"chẳng có gì cả", "éo trả lời"},1,"Thời gian thấm thoát qua mau quá hụ hụ.");
        themCauHoi(new String[]{"Sở thú bị cháy con gì chạy ra đầu tiên à?", "Sở thú thì Thú chạy trớc","Con chó xà mâu" ,"không có đáp án", "Con người"},4,"36 kế chạy trước là hạ sách kakak.");
        themCauHoi(new String[]{"Con gì đầu 'dê' mình 'ốc'?", "không có con đó","con dốc" ,"con dê-ốc", "thua"},2,"'dốc'==>đánh vần là:dê ốc dốc.");
        themCauHoi(new String[]{"Giải code: 1'=>pause=4505 có nghĩa là gì?", "Một phút bằng 4 năm không nằm ","1 phút suy nghĩ = bốn 5 không ngủ" ,"éo biết đâu", "1 phẩy suy ra ngưng =14"},2,"Hay tar..");
        themCauHoi(new String[]{"Một ngày đẹp trời ghệ of bạn Trung hỏi bạn Trung một câu.Anh ơi hôm nay trông em có xinh hok hihi.?", "Chắc có xinh mà hé","nó nghĩ gì sao tao biết" ,"em bị khùng hả,bố mầy xem C1", "trước mặt anh là một con heo."},2,"Haiz, hơi nhiều chuyện roy,để họ tự giải quyest đuê..");
        themCauHoi(new String[]{"Cái già ơ phía trước bạn mà bạn không thể tài nào nhìn thấy được?", "lời xin lỗi chân thành","cái trán" ,"tương lai", "cho qua đuê"},3,"Ai mà biết trước được tương lai đâu à.");
        themCauHoi(new String[]{"Cái gì to bằng con chó nhưng chẳng có tí tẹo gram nào cả?", "mây","con sói" ,"con hổ", "cái bóng"},4,"Thì là cái bóng con chó.");
        themCauHoi(new String[]{"Con khỉ sau khi thấy người đàn ông tắm ở suối,tại sao nó lại cười?", "tại ổng NUDE","đáp án khác" ,"khỉ cái mà ba", "éo biết câu trả lời"},2,"Nó thấy cái đuôi ông này mọc ngược lạ quá nên cười hahaha .");
        themCauHoi(new String[]{"Quả gì lắm múi nhiều khe ,anh hùng hào kiệt hoáng nge đã chạy dài", "sầu riêng","quả lựu đạn" ,"quả khé chua lét", "ta không sợ quả gì hết."},2,"Chạy càng xa càng tốt.");
        themCauHoi(new String[]{"Có cái thằng thích em gần phòng trọ.Anh gửi cho con gái ấy mảnh thư với nội dung:Em đồng ý làm bạn gái anh nha cô bé xinh xắn dễ thương.Kèm theo 3 đáp án" +":"+"A:ừm" +" - "+"B:chọn A" +" - "+"C:em đồng ý"+"\n"+"Hỏi cô ta từ chối hắn được hok?", "không thể","éo thèm trả lời thư" ,"éo biết","có đáp án khác nè."},2,"Thật tội cho chàng trai hụ hụ.");
    }
    //ham them cau hoi
    public void themCauHoi(String s[],int dapan,String ctl){
        row = new Vector();
        row.add(s[0]);
        row.add(s[1]);
        row.add(s[2]);
        row.add(s[3]);
        row.add(s[4]);
        row.add(dapan);
        row.add(ctl);
        cauhoi.add(row);
    }

    public void setanswer(Vector v){
        txcauhoi.startAnimation(AnimationUtils.loadAnimation(getApplication(),android.R.anim.slide_in_left));
        txcauhoi.setText(v.get(0).toString());
        btnA.setText(v.get(1).toString());
        btnB.setText(v.get(2).toString());
        btnC.setText((String) v.get(3));
        btnD.setText(v.get(4).toString());
        da = (int) v.get(5);
        dp = (String)v.get(6);
        soluongcauhoi--;
    }

    //bat su kien nut back ket thuc nhac:
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder bd=new AlertDialog.Builder(Traloicauhoi.this);
            bd.setTitle("Thông báo");
            bd.setMessage("Bạn muốn thoát ứng dụng!");
            //tao button ok:
            AlertDialog.Builder builder = bd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int which) {
                    finish();
                }
            });
            bd.setNegativeButton("No",new DialogInterface.OnClickListener() {
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
