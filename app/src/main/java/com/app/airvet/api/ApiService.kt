package com.app.airvet.api

import com.app.airvet.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("gender") gender: String
    ): Response<UserResponse>
}