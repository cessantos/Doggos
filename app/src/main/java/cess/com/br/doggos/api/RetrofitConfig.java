package cess.com.br.doggos.api;

import android.content.Context;

import javax.inject.Inject;

import cess.com.br.doggos.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConfig {

    @Inject
    public RetrofitConfig() {
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASIC_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitServices retrofitServices() {
        return retrofit.create(RetrofitServices.class);
    }

}
