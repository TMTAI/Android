package com.develop.tmtai.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.develop.tmtai.models.Schedule;
import com.develop.tmtai.travelmanagement.R;

import java.util.List;

/**
 * Created by tmtai on 8/10/2017.
 */

public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    private Activity context;
    private int resource;
    private List<Schedule> objects;

    public ScheduleAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Schedule> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = this.context.getLayoutInflater().inflate(resource, null);
        Schedule schedule = this.objects.get(position);
        if (schedule != null){
            TextView txtItemTime = (TextView) view.findViewById(R.id.txtItemTime);
            txtItemTime.setText(schedule.getTimeStart());
        }
        return view;
    }
}
