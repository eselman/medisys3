package com.eselman.medisys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.County;

import java.util.List;

/**
 * Created by eselman on 18/02/2017.
 */

public class CountiesAdapter extends BaseAdapter {
    private Context context;
    private List<County> counties;

    public CountiesAdapter(Context context, List<County> counties) {
        this.context = context;
        this.counties = counties;
    }

    @Override
    public int getCount() {
        return counties.size();
    }

    @Override
    public Object getItem(int i) {
        return counties.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.county_spinner_item, null, false);
        TextView countyCodeTextView = (TextView) view.findViewById(R.id.countyCode);
        TextView countyNameTextView = (TextView) view.findViewById(R.id.countyName);
        County county = (County) getItem(i);
        countyCodeTextView.setText(county.getCode());
        countyNameTextView.setText(county.getName());
        return view;
    }
}
