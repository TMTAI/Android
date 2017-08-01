package ctu.tmtai.com.quanlytiendien;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.api.MyProgressDialog;

import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHU_VUC;
import static ctu.tmtai.com.util.Constant.MA_THANH_PHO;
import static ctu.tmtai.com.util.Constant.TEN_KHU_VUC;

public class UserActivity extends AppCompatActivity
        implements ApiApp, NavigationView.OnNavigationItemSelectedListener {

    TextView txtCityName;
    // list view
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private ListView lvAreaCustomer;
    private MyProgressDialog myProgress;

    private JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        new ConnectHTTP().execute();
        myProgress = new MyProgressDialog(this);

        myProgress.showProgressBar();

        addControls();
        addEvents();

        setList("CẦN THƠ", "TP01");
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

        txtCityName = (TextView) findViewById(R.id.txtCityName);

        lvAreaCustomer = (ListView) findViewById(R.id.lvAreaCustomer);
        arrayList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayList
        );

        lvAreaCustomer.setAdapter(adapter);

    }

    @Override
    public void addEvents() {
        lvAreaCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAccount) {
            Intent intent = new Intent(getApplication(), InfomationUserActivity.class);
            startActivity(intent);
        } else if (id == R.id.navMap) {

        } else if (id == R.id.navFont) {

        } else if (id == R.id.navLanguage) {

        } else if (id == R.id.navLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.logout)
                    .setMessage(R.string.logout_message)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = MainActivity.preferences.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            startActivity(intent);
                            UserActivity.this.finish();
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
            setList("CẦN THƠ", "TP01");
        }else if (id == R.id.navHauGiang){
            setList("HẬU GIANG", "TP03");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setList(String tentp, String matp){
        txtCityName.setText(tentp);
        arrayList.clear();
        for (int i=0;i<jsonArray.length();i++){
            try {
                if (jsonArray.getJSONObject(i).getString(MA_THANH_PHO).toString().equals(matp)){
                    arrayList.add(jsonArray.getJSONObject(i).getString(TEN_KHU_VUC).toString());
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
                // đọc và chuyển về JSONObject
                jsonArray = new JSONArray(str);
                myProgress.closeProgressBar();

                Log.d("SKFJJDFKLGJKLDFGJDLFKGJ", jsonArray.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
