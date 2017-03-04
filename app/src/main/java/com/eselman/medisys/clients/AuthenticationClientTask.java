package com.eselman.medisys.clients;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.medisys.entities.LoginCredentials;
import com.eselman.medisys.entities.User;
import com.eselman.medisys.helpers.RetrofitHelper;
import com.eselman.medisys.rest.providers.AuthenticationRestProvider;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by eselman on 04/02/2017.
 */

public class AuthenticationClientTask extends AsyncTask {
    public interface Callback {
        void authenticationCallback(Object sessionKey);
    }
    private Callback callback;
    private Context context;

    public AuthenticationClientTask (Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String sessionKey = null;

        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            AuthenticationRestProvider authenticationRestProvider = retrofitHelper.createProvider(AuthenticationRestProvider.class, false);
            User user = (User) params[0];
            Call<LoginCredentials> call = authenticationRestProvider.authenticateUser(user);
            Response<LoginCredentials> response = call.execute();
            LoginCredentials loginCredentials = response.body();
            sessionKey = loginCredentials.getSessionKey();
        } catch (Exception e){
            e.printStackTrace();
        }

        return sessionKey;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        callback.authenticationCallback(o);
    }
}
