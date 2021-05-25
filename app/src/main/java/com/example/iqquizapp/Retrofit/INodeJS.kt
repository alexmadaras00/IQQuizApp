package com.example.iqquizapp.Retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface INodeJS {
    @POST("/register/")
    @FormUrlEncoded
    open fun registerUser(@Field("username") user: String?, @Field("password") password: String, @Field("email") email: String, @Field("phone") phone: String): io.reactivex.Observable<String>

    @POST("/login/")
    @FormUrlEncoded
    open fun loginUser(@Field("email") email: String, @Field("password") password: String): io.reactivex.Observable<String>

}