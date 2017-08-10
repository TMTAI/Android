package com.develop.tmtai.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.develop.tmtai.models.Traveler;
import com.develop.tmtai.travelmanagement.R;

import java.util.List;

/**
 * Created by tmtai on 8/10/2017.
 */

public class TravelerAdapter extends ArrayAdapter<Traveler> {
    private Activity context;
    private int resource;
    private List<Traveler> objects;

    public TravelerAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Traveler> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = this.context.getLayoutInflater().inflate(resource, null);
        Traveler traveler = this.objects.get(position);
        if (traveler != null){
            TextView txtItemName = (TextView) view.findViewById(R.id.txtItemName);
            txtItemName.setText(traveler.getName());
        }
        return view;
    }
}
