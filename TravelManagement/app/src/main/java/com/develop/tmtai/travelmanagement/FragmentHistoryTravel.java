package com.develop.tmtai.travelmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmtai on 8/10/2017.
 */

public class FragmentHistoryTravel extends Fragment {
    private List<String> arrayHistory;
    private ArrayAdapter<String> adapter;
    private ListView lvHistory;

    public FragmentHistoryTravel(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_travel, container, false);

        lvHistory = (ListView) view.findViewById(R.id.lvHistory);
        arrayHistory = new ArrayList<>();
        arrayHistory.add("Ninh Chữ");
        arrayHistory.add("Vũng Tàu");
        adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, arrayHistory
        );

        lvHistory.setAdapter(adapter);

        return view;
    }
}
