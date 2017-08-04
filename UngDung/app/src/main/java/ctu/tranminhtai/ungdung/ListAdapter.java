package ctu.tranminhtai.ungdung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tranm on 10/24/2016.
 */

public class ListAdapter extends ArrayAdapter<Words> {

    public ListAdapter(Context context, int resource, List<Words> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_dong_dictionary, null);
        }
        Words p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt1 = (TextView) view.findViewById(R.id.txtWord);
            txt1.setText(p.Word);

            TextView txt2 = (TextView) view.findViewById(R.id.txtNghia);
            txt2.setText(p.Nghia);

        }
        return view;
    }

}