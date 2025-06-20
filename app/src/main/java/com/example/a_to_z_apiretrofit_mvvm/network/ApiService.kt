package com.example.a_to_z_apiretrofit_mvvm.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<JsonElement>>

    @POST("posts")
    suspend fun createPost(@Body body: JsonElement): Response<JsonElement>

    @POST("posts")
    suspend fun createPostMedicineData(@Body body: JsonElement): Response<JsonElement>


}