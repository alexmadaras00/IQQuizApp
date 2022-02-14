package com.example.iqquizapp.repository.retrofit

import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.installations.Utils
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private lateinit var instance: Retrofit
    fun getInstance(): Retrofit {
        val port = "20222"
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        instance = Retrofit.Builder().baseUrl("http://192.168.1.150:20222")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
        return instance
    }


}