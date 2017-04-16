package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.PatientsRestProvider;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 14/04/2017.
 */

public class SavePatientClientTask extends AsyncTask {
    public interface Callback {
        void savePatientCallback(Patient patient);
    }
    private Callback callback;
    private Context context;
    private Patient patient;

    public SavePatientClientTask(Callback callback, Context context, Patient patient) {
        this.callback = callback;
        this.context = context;
        this.patient = patient;
    }

    @Override
    protected Patient doInBackground(Object[] params) {
        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            PatientsRestProvider patientsRestProvider =  retrofitHelper.createProvider(PatientsRestProvider.class, true);
            Call<Patient> call;
            if (patient.getId() != null) {
                call = patientsRestProvider.editPatient(patient);
            } else {
                call = patientsRestProvider.addPatient(patient);
            }
            Response<Patient> response = call.execute();
            patient = response.body();
        } catch (Exception e){
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    protected void onPostExecute(Object patient) {
        this.patient = (Patient) patient;
        callback.savePatientCallback(this.patient);
    }
}
