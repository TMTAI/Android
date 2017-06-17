package ctu.tranminhtai.ungdung;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class customRelativie extends ArrayAdapter<Integer> {
    Context context;
    int resource;
    Integer [] objects;
    Integer[]giatien={0,100,200,300,400,500};
    ArrayAdapter<Integer>adapter;
    public customRelativie(Context context, int resource, Integer[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        adapter=new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item,giatien);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, resource, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.ImageViewBanCo);
        Spinner spinner=(Spinner)view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        imageView.setImageResource(objects[position]);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position1, long id) {
                Baucua.gt[position]=giatien[position1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
}
