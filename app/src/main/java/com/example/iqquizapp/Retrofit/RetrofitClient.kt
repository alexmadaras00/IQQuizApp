package com.example.iqquizapp.Retrofit

import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {
    private lateinit var instance: Retrofit
    fun getInstance(): Retrofit {
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        instance = Retrofit.Builder().baseUrl("http://192.168.1.150:2022")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
        return instance
    }


}