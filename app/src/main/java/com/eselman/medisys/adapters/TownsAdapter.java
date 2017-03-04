package com.eselman.medisys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.Town;

import java.util.List;

/**
 * Created by eselman on 18/02/2017.
 */

public class TownsAdapter extends BaseAdapter {
    private Context context;
    private List<Town> towns;

    public TownsAdapter(Context context, List<Town> towns) {
        this.context = context;
        this.towns = towns;
    }

    @Override
    public int getCount() {
        return towns.size();
    }

    @Override
    public Object getItem(int i) {
        return towns.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.town_spinner_item, null, false);
        TextView townCodeTextView = (TextView) view.findViewById(R.id.townCode);
        TextView townNameTextView = (TextView) view.findViewById(R.id.townName);
        Town town = (Town) getItem(i);
        townCodeTextView.setText(town.getCode());
        townNameTextView.setText(town.getName());
        return view;
    }
}
