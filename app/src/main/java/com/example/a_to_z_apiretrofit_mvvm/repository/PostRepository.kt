package com.example.a_to_z_apiretrofit_mvvm.repository

import com.example.a_to_z_apiretrofit_mvvm.network.RetrofitClient
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response

class PostRepository {
    suspend fun getPosts(): Response<List<JsonElement>> {
        return RetrofitClient.instance.getPosts()
    }

    suspend fun createPost(
        title: String,
        name: String,
        gender: String,
        address: String
    ): Response<JsonElement> {
        val json = JsonObject().apply {
            addProperty("title", title)
            addProperty("body", name)
            addProperty("userId", 1)
            addProperty("gender", gender)
            addProperty("address", address)
        }
        return RetrofitClient.instance.createPost(json)
    }

    suspend fun createPostMedicineData(medicinename: String, quantity: Int): Response<JsonElement> {
        val json = JsonObject().apply {
            addProperty("medicine", medicinename)
            addProperty("quantity", quantity)
        }
        return RetrofitClient.instance.createPostMedicineData(json)
    }
}