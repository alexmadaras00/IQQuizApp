package com.example.iqquizapp.repository.retrofit

import com.example.iqquizapp.ui.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

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

    @POST("/")
    @FormUrlEncoded
    fun getDataUser(
        @Field("id") id: Int,
    ): Call<LoginResponse>

    @PUT("/updateTest1/")
    @FormUrlEncoded
    fun updateTest1(
        @Field("id") id: Int,
        @Field("progress") progress: Int,
        @Field("points") points: Int,
        @Field("done") done: Boolean
    ):Call<String>

    @PUT("/updateTest2/")
    @FormUrlEncoded
    fun updateTest2(
        @Field("id") id: Int,
        @Field("progress") progress: Int,
        @Field("points") points: Int,
        @Field("done") done: Boolean
    ):Call<String>

    @PUT("/updateTest3/")
    @FormUrlEncoded
    fun updateTest3(
        @Field("id") id: Int,
        @Field("progress") progress: Int,
        @Field("points") points: Int,
        @Field("done") done: Boolean
    ):Call<String>


}