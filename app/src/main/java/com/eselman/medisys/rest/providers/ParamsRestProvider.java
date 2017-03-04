package com.eselman.medisys.rest.providers;

import com.eselman.medisys.entities.County;
import com.eselman.medisys.entities.Department;
import com.eselman.medisys.entities.IdentificationType;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.entities.Town;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eselman on 18/02/2017.
 */

public interface ParamsRestProvider {
    @GET("person/identification-types")
    Call<List<IdentificationType>> getIdentificationTypes();

    @GET("address/towns/{departmentCode}")
    Call<List<Town>> getTowns(@Path("departmentCode") String departmentCode);

    @GET("address/departments/{countyCode}")
    Call<List<Department>> getDepartments(@Path("countyCode") String countyCode);

    @GET("address/counties/{countryCode}")
    Call<List<County>> getCounties(@Path("countryCode") String countryCode);
}
