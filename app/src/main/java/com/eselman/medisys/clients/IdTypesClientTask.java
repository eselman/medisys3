package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;
import com.eselman.medisys.entities.IdentificationType;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.ParamsRestProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 18/02/2017.
 */

public class IdTypesClientTask extends AsyncTask {
    public interface Callback {
        void getIdentificationTypesCallback(Object idTypes);
    }
    private IdTypesClientTask.Callback callback;
    private Context context;

    public IdTypesClientTask(IdTypesClientTask.Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<IdentificationType> idTypes = new ArrayList<>();

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            ParamsRestProvider paramsRestProvider =  retrofitHelper.createProvider(ParamsRestProvider.class, true);
            Call<List<IdentificationType>> call = paramsRestProvider.getIdentificationTypes();
            Response<List<IdentificationType>> response = call.execute();
            idTypes = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return idTypes;
    }

    @Override
    protected void onPostExecute(Object idTypes) {
        callback.getIdentificationTypesCallback(idTypes);
    }
}