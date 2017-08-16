package com.develop.tmtai.travelmanagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.develop.tmtai.adapter.ScheduleAdapter;
import com.develop.tmtai.models.Schedule;
import com.develop.tmtai.services.InternetConectionUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.develop.tmtai.util.Constant.HTTP_GET_ALL_SCHEDULE;


/**
 * Created by tmtai on 8/10/2017.
 */

public class FragmentSchedule extends Fragment {
    private ListView lvSchedule;
    private List<Schedule> listSchedule = new ArrayList<>();
    ;
    private ScheduleAdapter adapter;
    private ProgressDialog process;
    private ConnectionUtil connectionUtil;
    private JSONArray array;

    public FragmentSchedule() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        lvSchedule = (ListView) view.findViewById(R.id.lvSchedule);

        adapter = new ScheduleAdapter(
                getActivity(), R.layout.layout_schedule, listSchedule
        );

        lvSchedule.setAdapter(adapter);

        connectionUtil = new ConnectionUtil();
        connectionUtil.execute();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public class ConnectionUtil extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                array = InternetConectionUtil.getInstance().getJSONArrayFromServer(HTTP_GET_ALL_SCHEDULE);
                if (array!= null){
                    for (int i = 0; i < array.length(); i++) {
                        Schedule schedule = new Schedule(array.getJSONObject(i));
                        listSchedule.add(schedule);
                    }
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
