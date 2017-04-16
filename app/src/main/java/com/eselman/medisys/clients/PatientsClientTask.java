package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.PatientsRestProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 04/02/2017.
 */

public class PatientsClientTask extends AsyncTask {
    public interface Callback {
        void getPatientsListCallback(Object patients);
    }
    private Callback callback;
    private Context context;

    public PatientsClientTask (Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        List<Patient> patients = new ArrayList<>();

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            PatientsRestProvider patientsRestProvider =  retrofitHelper.createProvider(PatientsRestProvider.class, true);
            Call<List<Patient>> call = patientsRestProvider.getPatientsList();
            Response<List<Patient>> response = call.execute();
            patients = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    protected void onPostExecute(Object patients) {
        callback.getPatientsListCallback(patients);
    }
}
