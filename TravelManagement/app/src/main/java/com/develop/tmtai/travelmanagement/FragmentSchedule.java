package com.develop.tmtai.travelmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.develop.tmtai.adapter.ScheduleAdapter;
import com.develop.tmtai.models.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmtai on 8/10/2017.
 */

public class FragmentSchedule extends Fragment {
    private ListView lvSchedule;
    private List<Schedule> listSchedule;
    private ScheduleAdapter adapter;
    private Schedule schedule;
    public FragmentSchedule(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        schedule = new Schedule();
        schedule.setTimeStart("21h30");

        lvSchedule = (ListView) view.findViewById(R.id.lvSchedule);
        listSchedule = new ArrayList();
        listSchedule.add(schedule);
        schedule.setTimeStart("22h");
        listSchedule.add(schedule);
        adapter = new ScheduleAdapter(
                getActivity(), R.layout.layout_schedule, listSchedule
        );

        adapter.notifyDataSetChanged();
        lvSchedule.setAdapter(adapter);

        return view;
    }
}
