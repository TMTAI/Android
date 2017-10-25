package ctu.tmtai.com.quanlytiendien;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.adapter.CustomerAdapter;
import ctu.tmtai.com.adapter.UserAdapter;
import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.api.MyProgressDialog;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;

import static ctu.tmtai.com.util.Constant.*;

public class AdminActivity extends AppCompatActivity
        implements ApiApp, NavigationView.OnNavigationItemSelectedListener {

    private JSONArray arrayUser, arrayKhachHang;
    private MyJsonTask task = new MyJsonTask();
    private MyProgressDialog progressDialog;
    private TabHost tabHost;
    private FloatingActionButton fab;

    private ListView lvCustomer, lvEmployee;
    private UserAdapter userAdapter;
    private CustomerAdapter<KhachHang> customerAdapter;
    private List<User> userList;
    private List<KhachHang> customerList;
    private EditText txtResetNewPassword, txtResetConfirmPassword;
    private TextView txtPersonName, txtPersonPosition;
    private User user;
    private KhachHang khachHang;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        addControls();
        addEvents();
        loadUserList();
    }

    @Override
    public void addControls() {
        txtPersonName = (TextView) findViewById(R.id.txtPersonName);
        txtPersonPosition = (TextView) findViewById(R.id.txtPersonPosition);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ================================================= //
        fab = (FloatingActionButton) findViewById(R.id.fab);

        addTabHost();

        lvCustomer = (ListView) findViewById(R.id.lvCustomer);
        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        userList = new ArrayList<>();
        customerList = new ArrayList<>();

        userAdapter = new UserAdapter(
                this, R.layout.layout_custom_user, userList
        );

        lvEmployee.setAdapter(userAdapter);

        customerAdapter = new CustomerAdapter<KhachHang>(
                this, R.layout.layout_custom_customer, customerList
        );

        lvCustomer.setAdapter(customerAdapter);

        loadListToServer();
    }

    private void addTabHost() {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabEmployee = tabHost.newTabSpec(TAB_EMPLOYEE);
        tabEmployee.setContent(R.id.tabEmployee);
        tabEmployee.setIndicator("", getResources().getDrawable(R.drawable.employee));
        tabHost.addTab(tabEmployee);

        TabHost.TabSpec tabCustomer = tabHost.newTabSpec(TAB_CUSTOMER);
        tabCustomer.setContent(R.id.tabCustomer);
        tabCustomer.setIndicator("", getResources().getDrawable(R.drawable.customer));
        tabHost.addTab(tabCustomer);

    }

    private void addUserToList(JSONObject json) {
        User user = new User(json);
        userList.add(user);
        userAdapter.notifyDataSetChanged();
    }

    private void addKhachHangToList(JSONObject json) {
        KhachHang kh = new KhachHang(json);
        customerList.add(kh);
        customerAdapter.notifyDataSetChanged();
    }

    private void loadUserList() {
        if (arrayUser != null) {
            if (!userList.isEmpty()){
                userList.clear();
            }
            for (int i = 0; i < arrayUser.length(); i++) {
                try {
                    addUserToList(arrayUser.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadKhachHangList() {
        if (arrayKhachHang != null) {
            if (!customerList.isEmpty()){
                customerList.clear();
            }
            try {
                for (int i = 0; i < arrayKhachHang.length(); i++) {
                    addKhachHangToList(arrayKhachHang.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadListToServer() {
        if (!task.isCancelled() || task == null) {
            task.cancel(true);
            task = new MyJsonTask();
        }
        task.execute();

        loadUserList();
        // Hiển thị màn hình chờ khi hệ thống load dữ liệu
        progressDialog = new MyProgressDialog(this);
        progressDialog.showProgressBar();
    }

    @Override
    public void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                customerList.clear();
                userList.clear();
                if (tabId.equalsIgnoreCase(TAB_EMPLOYEE)) {
                    loadListToServer();
                    loadUserList();
                } else if (tabId.equalsIgnoreCase(TAB_CUSTOMER)) {
                    loadListToServer();
                    loadKhachHangList();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddUserActivity.class);
                createBundle(intent);
                startActivity(intent);
                finish();
            }
        });

        lvEmployee.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                newPassword(position, userList);
                return false;
            }
        });

        lvCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                newPassword(position, customerList);
                return false;
            }
        });
    }

    private void delete(final int position, final List list) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminActivity.this);
        alertDialogBuilder
                .setTitle(R.string.delete)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try {
                            if (list == userList) {
                                user = userList.get(position);
                                new deleteUserConnection().execute();
                            } else {
                                khachHang = customerList.get(position);
                                new deleteKhachHangConnection().execute();
                            }
                        } catch (Exception e) {
                            Log.d(ERROR, e.getLocalizedMessage());
                        }
                        Notify.showToast(getApplicationContext(), R.string.Delete_completed, Notify.SHORT);
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void newPassword(final int position, final List list) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_reset_password_user, null);

        txtResetNewPassword = (EditText) alertLayout.findViewById(R.id.txtResetNewPassword);
        txtResetConfirmPassword = (EditText) alertLayout.findViewById(R.id.txtResetConfirmPassword);

        alertDialogBuilder
                .setTitle(R.string.change_password)
                .setView(alertLayout)
                .setPositiveButton("New Password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (!txtResetNewPassword.getText().toString().equals("") && txtResetNewPassword.getText().toString().equals(txtResetConfirmPassword.getText().toString())) {
                            try {
                                if (list == userList) {
                                    user = userList.get(position);
                                    user.setPassword(txtResetConfirmPassword.getText().toString());
                                    task.cancel(true);
                                    new UserConnection().execute();
                                } else {
                                    khachHang = customerList.get(position);
                                    khachHang.setPassword(txtResetConfirmPassword.getText().toString());
                                    task.cancel(true);
                                    new KhachHangConnection().execute();
                                }
                            } catch (Exception e) {
                                Log.d(ERROR, e.getLocalizedMessage());
                            }

                            Notify.showToast(AdminActivity.this, R.string.change_susscess, Notify.SHORT);
                        }
                    }
                })
                .setNegativeButton("Delete User", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(position, list);
                        loadListToServer();
                        loadUserList();
                        loadKhachHangList();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            logout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem mnuSearch = menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) mnuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userList.clear();
                customerList.clear();
                try {
                    for (int i = 0; i < arrayUser.length(); i++) {
                        if (arrayUser.getJSONObject(i).get(NAME).toString().toUpperCase().indexOf(newText.toUpperCase()) > -1) {
                            addUserToList(arrayUser.getJSONObject(i));
                        }
                    }
                    for (int i = 0; i < arrayKhachHang.length(); i++) {
                        if ((arrayKhachHang.getJSONObject(i).get(TEN_KH).toString().toUpperCase().indexOf(newText.toUpperCase()) > -1)) {
                            addKhachHangToList(arrayKhachHang.getJSONObject(i));
                        }
                    }


                    if (userList.isEmpty() && customerList.isEmpty()) {
                        for (int i = 0; i < arrayUser.length(); i++) {
                            if (arrayUser.getJSONObject(i).get(ADDRESS).toString().toUpperCase().indexOf(newText.toUpperCase()) > -1) {
                                addUserToList(arrayUser.getJSONObject(i));
                            }
                        }

                        for (int i = 0; i < arrayKhachHang.length(); i++) {
                            if ((arrayKhachHang.getJSONObject(i).get(DIA_CHI).toString().toUpperCase().indexOf(newText.toUpperCase()) > -1)) {
                                addKhachHangToList(arrayKhachHang.getJSONObject(i));
                            }
                        }
                    }
                    userAdapter.notifyDataSetChanged();
                    customerAdapter.notifyDataSetChanged();
                } catch (
                        JSONException e)

                {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return true;
    }

    private void createBundle(Intent intent) {
        bundle = new Bundle();
        bundle.putString(ROLE, ROLE_ADMIN);
        bundle.putSerializable(USER,user);
        intent.putExtra(BUNDLE_USER, bundle);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAccountAdmin) {
            Intent intent = new Intent(getApplicationContext(), InfomationUserActivity.class);
            createBundle(intent);
            startActivity(intent);
        } else if (id == R.id.navAddArea) {

        } else if (id == R.id.navChangePassword) {
            Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
            createBundle(intent);
            startActivity(intent);
        } else if (id == R.id.navLanguageAdmin) {

        } else if (id == R.id.navLogoutAdmin) {
            logout();
        } else if (id == R.id.navFontAdmin) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
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
                        AdminActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.i_think_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                Document documentKhachHang = Jsoup.connect(HTTP_ALL_KHACH_HANG).get();
                String strAllKhachHang = documentKhachHang.body().text();
                arrayKhachHang = new JSONArray(strAllKhachHang);

                Document documentUser = Jsoup.connect(HTTP_ALL_USER).get();
                String strAllUser = documentUser.body().text();
                arrayUser = new JSONArray(strAllUser);

                user = new User(arrayUser.getJSONObject(0));
                progressDialog.closeProgressBar();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            // ta cập nhật giao diện ở đây:
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }

    public class UserConnection extends AsyncTask<String, Object, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_NEW_PASSWORD, "User");
                Jsoup.connect(url).data(CODE, user.getCode())
                        .data(NEW_PASS, user.getPassword()).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class deleteUserConnection extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_DELETE, "User");
                Jsoup.connect(url).data(CODE, user.getCode()).get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class deleteKhachHangConnection extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_DELETE, "KhachHang");
                Jsoup.connect(url).data(MA_KH, khachHang.getMakh()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class KhachHangConnection extends AsyncTask<String, Object, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = String.format(HTTP_NEW_PASSWORD, "KhachHang");
                Jsoup.connect(url).data(MA_KH, khachHang.getMakh())
                        .data(NEW_PASS, khachHang.getPassword()).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
