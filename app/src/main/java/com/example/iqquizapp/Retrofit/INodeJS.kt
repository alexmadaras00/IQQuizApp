package com.example.iqquizapp.Retrofit

import com.example.iqquizapp.ui.login.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface INodeJS {
    @POST("/register/")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") user: String?,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone: String
    ): io.reactivex.Observable<String>

    @POST("/login/")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>



}