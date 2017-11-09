package ctu.tmtai.com.quanlytiendien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.adapter.DienKeAdaper;
import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.DienKe;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.Nam;

import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.CHI_SO_MOI;
import static ctu.tmtai.com.util.Constant.HTTP_GET_DIEN_KE_BY_MA_KH;
import static ctu.tmtai.com.util.Constant.HTTP_GET_NAM;
import static ctu.tmtai.com.util.Constant.HTTP_UPDATE_CHI_SO_DIEN_KE;
import static ctu.tmtai.com.util.Constant.ID;
import static ctu.tmtai.com.util.Constant.IS_THANH_TOAN;
import static ctu.tmtai.com.util.Constant.MA_KH;
import static ctu.tmtai.com.util.Constant.USER;

public class ElectricMeterInfomationActivity extends AppCompatActivity implements ApiApp {

    private TextView txtNameCustomer;
    private KhachHang khachHang;
    private DienKe dk;

    private ArrayList<String> listNam, listThang;
    private ArrayAdapter<String> adapterNam;
    private DienKeAdaper dienKeAdaper;
    private List<DienKe> listDienKe, listDienKeByYear;
    private ListView lvDienKe;
    private Spinner spNam;
    private String nam;
    private int chisomoi;
    private YearConnection yearConnection = new YearConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_meter_infomation);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {
        spNam = (Spinner) findViewById(R.id.spNam);
        txtNameCustomer = (TextView) findViewById(R.id.txtNameCustomer);
        listNam = new ArrayList<>();
        listDienKe = new ArrayList<DienKe>();
        listThang = new ArrayList<>();
        lvDienKe = (ListView) findViewById(R.id.lvDienKe);
        listDienKeByYear = new ArrayList<DienKe>();

        adapterNam = new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_spinner_item, listNam
        );

        adapterNam.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spNam.setAdapter(adapterNam);

        dienKeAdaper = new DienKeAdaper(
                this, R.layout.layout_list_dien_ke, listDienKeByYear
        );
        lvDienKe.setAdapter(dienKeAdaper);

        yearConnection.execute();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(BUNDLE_USER);

        if (bundle != null){
            khachHang = (KhachHang) bundle.getSerializable(USER);
            txtNameCustomer.setText(khachHang.getTenkh().toString());
        }
    }

    @Override
    public void addEvents() {
        spNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listDienKeByYear.clear();

                if (!yearConnection.isCancelled()){
                    yearConnection.cancel(true);
                }

                nam = listNam.get(position);

                for (DienKe dienKe:listDienKe){
                    if (dienKe.getManam().equalsIgnoreCase(nam)){
                        listDienKeByYear.add(dienKe);
                    }
                }

                dienKeAdaper.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       lvDienKe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               showLayoutDialog(position);
               return false;
           }
       });
    }

    public void showLayoutDialog(int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ElectricMeterInfomationActivity.this);
        View alertLayout = getLayoutInflater().inflate(R.layout.layout_write_electrical_indicator, null);

        dk = listDienKeByYear.get(position);

        TextView txtOldNubmer = (TextView) alertLayout.findViewById(R.id.txtOldNumber);
        final EditText txtNewNumber = (EditText) alertLayout.findViewById(R.id.txtNewNumber);

        if (dk != null){
            txtOldNubmer.setText(dk.getChisocu().toString());
            txtNewNumber.setText(dk.getChisomoi().toString());
        }

        alertDialogBuilder
                .setTitle(R.string.change_password)
                .setView(alertLayout)
                .setPositiveButton(getString(R.string.new_number), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        chisomoi = Integer.parseInt(txtNewNumber.getText().toString());

                        new UpdateDienKeConnection().execute("false");

                        Intent intent = new Intent(getApplicationContext(), ElectricMeterInfomationActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(USER, khachHang);
                        intent.putExtra(BUNDLE_USER, bundle);
                        startActivity(intent);
                        finish();
                        Log.d("New Number", txtNewNumber.getText().toString());
                    }
                })
                .setNegativeButton(getString(R.string.pay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chisomoi = Integer.parseInt(txtNewNumber.getText().toString());
                        new UpdateDienKeConnection().execute("true");

                        Intent intent = new Intent(getApplicationContext(), ElectricMeterInfomationActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(USER, khachHang);
                        intent.putExtra(BUNDLE_USER, bundle);
                        startActivity(intent);
                        finish();
                        Log.d("New Number", txtNewNumber.getText().toString());
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public class YearConnection extends  AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String str = Jsoup.connect(HTTP_GET_NAM).get().body().text();
                JSONArray jsonArray = new JSONArray(str);
                for (int i=0;i<jsonArray.length(); i++){
                    Nam nam = new Nam(jsonArray.getJSONObject(i));
                    listNam.add(nam.getManam());
                }
                nam = listNam.get(0);
                adapterNam.notifyDataSetChanged();

                String str2 = Jsoup.connect(HTTP_GET_DIEN_KE_BY_MA_KH).data(MA_KH, khachHang.getMakh()).get().body().text();
                JSONArray jsonArray2 = new JSONArray(str2);

                Log.d("Dien Ke ==" , str2);
                for (int i=0;i<jsonArray2.length(); i++){
                    DienKe dienKe = new DienKe(jsonArray2.getJSONObject(i));
                    listDienKe.add(dienKe);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class UpdateDienKeConnection extends  AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String isthanhtoan = params[0];
                Jsoup.connect(HTTP_UPDATE_CHI_SO_DIEN_KE)
                        .data(ID, dk.getId())
                        .data(CHI_SO_MOI, String.valueOf(chisomoi))
                        .data(IS_THANH_TOAN, isthanhtoan).post();


                String str2 = Jsoup.connect(HTTP_GET_DIEN_KE_BY_MA_KH).data(MA_KH, khachHang.getMakh()).get().body().text();
                JSONArray jsonArray2 = new JSONArray(str2);

                Log.d("Dien Ke ==" , str2);
                for (int i=0;i<jsonArray2.length(); i++){
                    DienKe dienKe = new DienKe(jsonArray2.getJSONObject(i));
                    listDienKe.add(dienKe);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
