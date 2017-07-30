package ctu.tmtai.com.quanlytiendien;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.service.UserService;

import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHACH_HANG;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_USER;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.ROLE_CUSTOMER;
import static ctu.tmtai.com.util.Constant.ROLE_EMPLOYEE;
import static ctu.tmtai.com.util.Constant.TAB_CUSTOMER;
import static ctu.tmtai.com.util.Constant.TAB_EMPLOYEE;

public class AdminActivity extends AppCompatActivity
        implements ApiApp, NavigationView.OnNavigationItemSelectedListener {

    private JSONArray arrayUser, arrayKhachHang;
    MyJsonTask task = new MyJsonTask();
    TabHost tabHost;
    FloatingActionButton fab;

    ListView lvCustomer, lvEmployee;
    UserAdapter userAdapter;
    CustomerAdapter customerAdapter;
    List<User> userList, customerList;
    EditText txtResetNewPassword, txtResetConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addControls();
        addEvents();

        task.execute();

        customerAdapter.notifyDataSetChanged();
        userAdapter.notifyDataSetChanged();
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

        customerAdapter = new CustomerAdapter(
                this, R.layout.layout_custom_customer, customerList
        );

        lvCustomer.setAdapter(customerAdapter);

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

    private void addUserToList(List list, JSONObject json) {
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(json.toString(), User.class);
        list.add(user);
    }

    private void loadList() {
        customerList.clear();
        userList.clear();
        try {
            for (int i = 0; i < arrayUser.length(); i++) {
                addUserToList(userList, arrayUser.getJSONObject(i));
            }
            for (int i = 0; i < arrayKhachHang.length(); i++) {
                addUserToList(customerList, arrayKhachHang.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        customerAdapter.notifyDataSetChanged();
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase(TAB_EMPLOYEE)) {
                    loadList();
                } else if (tabId.equalsIgnoreCase(TAB_EMPLOYEE)) {
                    loadList();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddUserActivity.class);
                startActivity(intent);
            }
        });

        lvEmployee.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()

        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_reset_password_user, null);

                txtResetNewPassword = (EditText) alertLayout.findViewById(R.id.txtResetNewPassword);
                txtResetConfirmPassword = (EditText) alertLayout.findViewById(R.id.txtResetConfirmPassword);

                alertDialogBuilder
                        .setTitle(R.string.change_password)
                        .setView(alertLayout)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                User user = userList.get(position);

                                if (!txtResetNewPassword.getText().toString().equals("") && txtResetNewPassword.getText().toString().equals(txtResetConfirmPassword.getText().toString())) {
                                    user.setPassword(txtResetConfirmPassword.getText().toString());
                                    UserService.updateUser(user, AdminActivity.this);
                                    loadList();
                                } else {
                                    Notify.showToast(AdminActivity.this, R.string.password_empty, Notify.SHORT);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });
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
            super.onBackPressed();
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
                        if (arrayUser.getJSONObject(i).get("name").toString().toUpperCase().indexOf(newText.toUpperCase()) > -1) {
                            addUserToList(userList, arrayUser.getJSONObject(i));
                        }
                    }

                    for (int i = 0; i < arrayKhachHang.length(); i++) {
                        if ((arrayKhachHang.getJSONObject(i).get("name").toString().toUpperCase().indexOf(newText.toUpperCase()) > -1)) {
                            addUserToList(customerList, arrayKhachHang.getJSONObject(i));
                        }
                    }
                    userAdapter.notifyDataSetChanged();
                    customerAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAccountAdmin) {
            Intent intent = new Intent(getApplicationContext(), InfomationUserActivity.class);
            startActivity(intent);
        } else if (id == R.id.navAddArea) {

        } else if (id == R.id.navChangePassword) {
            Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
            startActivity(intent);

        } else if (id == R.id.navLanguageAdmin) {

        } else if (id == R.id.navLogoutAdmin) {
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
        } else if (id == R.id.navFontAdmin) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<Object, Object, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Object... params) {
            try {
                Document documentKhachHang = Jsoup.connect(HTTP_ALL_KHACH_HANG).get();
                String strAllKhachHang = documentKhachHang.body().text();
                arrayKhachHang = new JSONArray(strAllKhachHang);

                Document documentUser = Jsoup.connect(HTTP_ALL_USER).get();
                String strAllUser = documentKhachHang.body().text();
                arrayUser = new JSONArray(strAllUser);

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
//            // ta cập nhật giao diện ở đây:
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }
}
