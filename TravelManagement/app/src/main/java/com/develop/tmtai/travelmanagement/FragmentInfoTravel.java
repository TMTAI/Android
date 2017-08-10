package com.develop.tmtai.travelmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.develop.tmtai.adapter.TravelerAdapter;
import com.develop.tmtai.models.Traveler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmtai on 8/10/2017.
 */

public class FragmentInfoTravel extends Fragment {
    private ListView lvTraveler;
    private List<Traveler> travelerList;
    private TravelerAdapter adapter;

    public FragmentInfoTravel(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_travel, container, false);
        travelerList = new ArrayList<Traveler>();
        Traveler traveler = new Traveler();
        traveler.setName("Minh Tai");
        travelerList.add(traveler);
        lvTraveler = (ListView) view.findViewById(R.id.lvTraveler);
        adapter = new TravelerAdapter(
                getActivity(), R.layout.layout_traveler, travelerList
        );
        adapter.notifyDataSetChanged();
        lvTraveler.setAdapter(adapter);
        return view;
    }
}
