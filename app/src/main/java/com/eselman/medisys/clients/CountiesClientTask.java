package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.County;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.ParamsRestProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 18/02/2017.
 */

public class CountiesClientTask extends AsyncTask {
    public interface Callback {
        void getCountiesCallback(Object counties);
    }
    private CountiesClientTask.Callback callback;
    private Context context;
    private String countryCode;

    public CountiesClientTask(CountiesClientTask.Callback callback, Context context, String countryCode) {
        this.callback = callback;
        this.context = context;
        this.countryCode = countryCode;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<County> counties = new ArrayList<>();

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            ParamsRestProvider paramsRestProvider =  retrofitHelper.createProvider(ParamsRestProvider.class, true);
            Call<List<County>> call = paramsRestProvider.getCounties(countryCode);
            Response<List<County>> response = call.execute();
            counties = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return counties;
    }

    @Override
    protected void onPostExecute(Object counties) {
        super.onPostExecute(counties);
        callback.getCountiesCallback(counties);
    }
}