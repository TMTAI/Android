package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.util.JSONReader;

import static ctu.tmtai.com.util.Constant.HTTP_CODE_USER;
import static ctu.tmtai.com.util.Constant.USERNAME;

public class UserActivity extends AppCompatActivity
        implements ApiApp, NavigationView.OnNavigationItemSelectedListener {


    // list view
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private ListView lvAreaCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        addControls();
        addEvents();

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



        lvAreaCustomer = (ListView) findViewById(R.id.lvAreaCustomer);
        arrayList = new ArrayList<>();
        arrayList.add("Khu Vuc 1");
        arrayList.add("Khu Vuc 2");
        arrayList.add("Khu Vuc 3");
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

        } else if (id == R.id.navHistory){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
