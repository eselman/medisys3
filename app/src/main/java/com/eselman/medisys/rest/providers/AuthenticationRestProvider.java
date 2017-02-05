package com.eselman.medisys.rest.providers;

import com.eselman.medisys.entities.LoginCredentials;
import com.eselman.medisys.entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by eselman on 04/02/2017.
 */

public interface AuthenticationRestProvider {
    @POST("auth/login")
    Call<LoginCredentials> authenticateUser(@Body User user);
}
