package com.eselman.medisys.helpers;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.LocalDateTime;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eselman on 04/02/2017.
 */

public class RetrofitHelper {
    private static final String SERVER_BASE_URL = "http://192.168.1.35:8080/medisys/";
    private static final String AUTH_TOKEN = "MedisysToken";

    private OkHttpClient.Builder httpClient;

    private Retrofit.Builder builder;

    private Retrofit retrofit;

    private Context context;

    public RetrofitHelper(Context context) {
        this.context = context;
        this.httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
                .create();
        builder = new Retrofit.Builder()
                .baseUrl(SERVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    public <S> S createProvider(Class<S> serviceClass, boolean requireAuth) {
        SessionHelper sessionHelper = SessionHelper.getInstance();
        String authToken = AUTH_TOKEN + " " + sessionHelper.getSessionKey(context);
        return createProvider(serviceClass, authToken);
    }

    public <S> S createProvider(Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
