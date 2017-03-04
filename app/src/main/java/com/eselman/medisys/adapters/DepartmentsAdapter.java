package com.eselman.medisys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.Department;

import java.util.List;

/**
 * Created by eselman on 18/02/2017.
 */

public class DepartmentsAdapter extends BaseAdapter {
    private Context context;
    private List<Department> departments;

    public DepartmentsAdapter(Context context, List<Department> departments) {
        this.context = context;
        this.departments = departments;
    }

    @Override
    public int getCount() {
        return departments.size();
    }

    @Override
    public Object getItem(int i) {
        return departments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.department_spinner_item, null, false);
        TextView departmentCodeTextView = (TextView) view.findViewById(R.id.departmentCode);
        TextView departmentNameTextView = (TextView) view.findViewById(R.id.departmentName);
        Department department = (Department) getItem(i);
        departmentCodeTextView.setText(department.getCode());
        departmentNameTextView.setText(department.getName());
        return view;
    }
}
