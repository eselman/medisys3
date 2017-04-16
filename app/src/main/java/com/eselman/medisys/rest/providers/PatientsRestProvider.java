package com.eselman.medisys.rest.providers;

import com.eselman.medisys.entities.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Created by eselman on 04/02/2017.
 */

public interface PatientsRestProvider {
    @GET("person/patients")
    Call<List<Patient>> getPatientsList();

    @PUT("person/patient")
    Call<Patient> editPatient(@Body Patient patient);

    @PUT("person/patient")
    Call<Patient> addPatient(@Body Patient patient);
}
