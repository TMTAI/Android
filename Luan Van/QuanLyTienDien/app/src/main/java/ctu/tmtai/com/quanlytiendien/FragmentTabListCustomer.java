package ctu.tmtai.com.quanlytiendien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ctu.tmtai.com.adapter.CustomerAdapter;
import ctu.tmtai.com.models.DienKe;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.KhuVuc;

import static ctu.tmtai.com.util.Constant.BUNDLE_KHU_VUC;
import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHACH_HANG_THEO_KHU_VUC;
import static ctu.tmtai.com.util.Constant.KHU_VUC;
import static ctu.tmtai.com.util.Constant.MA_KHU_VUC;
import static ctu.tmtai.com.util.Constant.USER;

/**
 * Created by tranm on 05-Aug-17.
 */

public class FragmentTabListCustomer extends Fragment {
    private ListView lvCustomer;
    private List<KhachHang> khachHangList;
    private CustomerAdapter adapter;
    private CustomerAdapter<KhachHang> arrayAdapter;
    private TextView txtNameKhuVuc;
    private KhuVuc kv;
    private LayoutInflater inflater;
    private TextView txtOldNubmer, txtNewNumber;
    private FloatingActionButton btnUpdateComplete, btnSubmit;
    private DienKe dk;
    private KhachHang kh;
    public FragmentTabListCustomer(){
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        this.inflater = inflater;
        khachHangList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_tab_list_customer, container, false);

        txtNameKhuVuc = (TextView) view.findViewById(R.id.txtNameKhuVuc);

        lvCustomer = (ListView) view.findViewById(R.id.lvTabListCustomer);

        arrayAdapter = new CustomerAdapter<KhachHang>(
                getActivity(), R.layout.layout_custom_customer,khachHangList
        );

        lvCustomer.setAdapter(arrayAdapter);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra(BUNDLE_KHU_VUC);
        if (bundle != null){
            kv = (KhuVuc) bundle.getSerializable(KHU_VUC);
            txtNameKhuVuc.setText(getString(R.string.list_customer_of) + " " + kv.getTenkv());
        }

        new ConnectionServer().execute();


        addEvents();
        return view;
    }

    private void addEvents(){
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhachHang khachHang = khachHangList.get(position);

                Intent intent = new Intent(getContext(), ElectricMeterInfomationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(USER, khachHang);
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
            }
        });
    }

    public class ConnectionServer extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                String str = Jsoup.connect(HTTP_ALL_KHACH_HANG_THEO_KHU_VUC).data(MA_KHU_VUC, kv.getMakv()).get().body().text();
                Log.d("STRING = ", str);
                JSONArray arrayKhachHang = new JSONArray(str);
                KhachHang khachHang;
                for (int i=0;i<arrayKhachHang.length();i++){
                    khachHang = new KhachHang(arrayKhachHang.getJSONObject(i));
                    khachHangList.add(khachHang);
                }

                arrayAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
