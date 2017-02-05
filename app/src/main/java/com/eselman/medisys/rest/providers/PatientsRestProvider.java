package com.eselman.medisys.rest.providers;

import com.eselman.medisys.entities.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eselman on 04/02/2017.
 */

public interface PatientsRestProvider {

    @GET("person/patients")
    Call<List<Patient>> getPatientsList();
}
