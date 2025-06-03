package com.example.a_to_z_apiretrofit_mvvm.repository

import com.example.a_to_z_apiretrofit_mvvm.network.RetrofitClient
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response

class PostRepository {
    suspend fun getPosts(): Response<List<JsonElement>> {
        return RetrofitClient.instance.getPosts()
    }

    suspend fun createPost(title: String, body: String): Response<JsonElement> {
        val json = JsonObject().apply {
            addProperty("title", title)
            addProperty("body", body)
            addProperty("userId", 1)
        }
        return RetrofitClient.instance.createPost(json)
    }
}
