package com.example.retrofitoperationapp.api

import com.example.retrofitoperationapp.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page:Int
    ): Response<User>

}