package ctu.tmtai.com.quanlytiendien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.internal.na;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.api.MyProgressDialog;
import ctu.tmtai.com.models.KhuVuc;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;

import static ctu.tmtai.com.util.Constant.BUNDLE_KHU_VUC;
import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHACH_HANG_THEO_KHU_VUC;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHU_VUC;
import static ctu.tmtai.com.util.Constant.KHU_VUC;
import static ctu.tmtai.com.util.Constant.MA_KHU_VUC;
import static ctu.tmtai.com.util.Constant.MA_THANH_PHO;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.ROLE_EMPLOYEE;
import static ctu.tmtai.com.util.Constant.TEN_KHU_VUC;
import static ctu.tmtai.com.util.Constant.USER;

public class UserActivity extends AppCompatActivity
        implements ApiApp, NavigationView.OnNavigationItemSelectedListener {

    TextView txtCityName, txtPersonNameUser, txtPersonPositionUser;

    // list view
    private ArrayList<String> arrayListTenKhuVuc;
    private ArrayAdapter<String> adapter;
    private ListView lvAreaCustomer;
    private JSONArray jsonArray;

    private Bundle bundle;
    private User user;
    private List<KhuVuc> listKhuVuc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        new ConnectHTTP().execute();

        addControls();
        addEvents();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setList("Cần Thơ", "TP01");

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE_USER);
        if (bundle!= null){
            user = (User) bundle.getSerializable(USER);

            txtPersonNameUser.setText(user.getName());
            txtPersonPositionUser.setText(user.getRole());
        }
    }

    @Override
    public void addControls() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        txtPersonNameUser = (TextView) header.findViewById(R.id.txtPersonNameUser);
        txtPersonPositionUser = (TextView) header.findViewById(R.id.txtPersonPositionUser);

        txtCityName = (TextView) findViewById(R.id.txtCityName);

        lvAreaCustomer = (ListView) findViewById(R.id.lvAreaCustomer);
        arrayListTenKhuVuc = new ArrayList<>();

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayListTenKhuVuc
        );

        lvAreaCustomer.setAdapter(adapter);
    }

    @Override
    public void addEvents() {
        lvAreaCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                String tenkv = arrayListTenKhuVuc.get(position);

                for (int i=0;i<listKhuVuc.size();i++){
                    if (tenkv.equalsIgnoreCase(listKhuVuc.get(i).getTenkv())){

                        bundle.putSerializable(KHU_VUC, listKhuVuc.get(i));
                    }
                }

                Intent intent = new Intent(getApplicationContext(), ListCustomerActivity.class);
                intent.putExtra(BUNDLE_KHU_VUC, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void createBundle(Intent intent) {
        bundle = new Bundle();
        bundle.putString(ROLE, ROLE_EMPLOYEE);
        bundle.putSerializable(USER, user);
        intent.putExtra(BUNDLE_USER, bundle);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAccount) {
            Intent intent = new Intent(getApplicationContext(), InfomationUserActivity.class);
            createBundle(intent);
            startActivity(intent);
            finish();
        }else if (id == R.id.navChangePassword) {
            Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
            createBundle(intent);
            startActivity(intent);
            finish();
        } else if (id == R.id.navLanguage) {

        } else if (id == R.id.navLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.logout)
                    .setMessage(R.string.logout_message)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.i_think_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.navCanTho){
            setList("Cần Thơ", "TP01");
        }else if (id == R.id.navHauGiang){
            setList("Hậu Giang", "TP03");
        }else if (id == R.id.navHCM){
            setList("Tp. HCM", "TP02");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setList(String tentp, String matp){
        txtCityName.setText(tentp);
        arrayListTenKhuVuc.clear();
        for (int i=0;i<jsonArray.length();i++){
            try {
                if (jsonArray.getJSONObject(i).getString(MA_THANH_PHO).toString().equals(matp)){
                    arrayListTenKhuVuc.add(jsonArray.getJSONObject(i).getString(TEN_KHU_VUC).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter.notifyDataSetChanged();
    }

    public class ConnectHTTP extends AsyncTask<String, JSONObject, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                Connection connection = Jsoup.connect(HTTP_ALL_KHU_VUC);
                Document document = connection.get();
                String str = document.body().text();

                jsonArray = new JSONArray(str);

                for (int i=0;i<jsonArray.length();i++){
                    KhuVuc kv = new KhuVuc();
                    kv.setMakv(jsonArray.getJSONObject(i).getString(MA_KHU_VUC));
                    kv.setTenkv(jsonArray.getJSONObject(i).getString(TEN_KHU_VUC));
                    kv.setMatp(jsonArray.getJSONObject(i).getString(MA_THANH_PHO));
                    listKhuVuc.add(kv);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
