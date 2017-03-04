package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.Town;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.ParamsRestProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 18/02/2017.
 */

public class TownsClientTask extends AsyncTask {
    public interface Callback {
        void getTownsCallback(Object towns);
    }
    private TownsClientTask.Callback callback;
    private Context context;
    private String departmentCode;

    public TownsClientTask(TownsClientTask.Callback callback, Context context, String departmentCode) {
        this.callback = callback;
        this.context = context;
        this.departmentCode = departmentCode;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<Town> towns = new ArrayList<>();

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            ParamsRestProvider paramsRestProvider =  retrofitHelper.createProvider(ParamsRestProvider.class, true);
            Call<List<Town>> call = paramsRestProvider.getTowns(departmentCode);
            Response<List<Town>> response = call.execute();
            towns = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return towns;
    }

    @Override
    protected void onPostExecute(Object towns) {
        super.onPostExecute(towns);
        callback.getTownsCallback(towns);
    }
}