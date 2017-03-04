package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.Department;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.ParamsRestProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 18/02/2017.
 */

public class DepartmentsClientTask extends AsyncTask {
    public interface Callback {
        void getDepartmentsCallback(Object departments);
    }
    private DepartmentsClientTask.Callback callback;
    private Context context;
    private String countyCode;

    public DepartmentsClientTask(DepartmentsClientTask.Callback callback, Context context, String countyCode) {
        this.callback = callback;
        this.context = context;
        this.countyCode = countyCode;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<Department> departments = new ArrayList<>();

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            ParamsRestProvider paramsRestProvider =  retrofitHelper.createProvider(ParamsRestProvider.class, true);
            Call<List<Department>> call = paramsRestProvider.getDepartments(countyCode);
            Response<List<Department>> response = call.execute();
            departments = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return departments;
    }

    @Override
    protected void onPostExecute(Object departments) {
        super.onPostExecute(departments);
        callback.getDepartmentsCallback(departments);
    }
}