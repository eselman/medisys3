package com.eselman.medisys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.IdentificationType;

import java.util.List;

/**
 * Created by eselman on 18/02/2017.
 */

public class IdTypesAdapter extends BaseAdapter {
    private Context context;
    private List<IdentificationType> idTypes;

    public IdTypesAdapter(Context context, List<IdentificationType> idTypes) {
        this.context = context;
        this.idTypes = idTypes;
    }

    @Override
    public int getCount() {
        return idTypes.size();
    }

    @Override
    public Object getItem(int i) {
        return idTypes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.id_type_spinner_item, null, false);
        TextView idTypeCodeTextView = (TextView) view.findViewById(R.id.idTypeCode);
        IdentificationType idType = (IdentificationType) getItem(i);
        idTypeCodeTextView.setText(idType.getCode());
        return view;
    }
}
