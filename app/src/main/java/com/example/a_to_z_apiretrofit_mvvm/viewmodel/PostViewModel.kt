package com.example.a_to_z_apiretrofit_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a_to_z_apiretrofit_mvvm.repository.PostRepository
import com.google.gson.JsonElement
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()
    val obsResponse = MutableLiveData<List<JsonElement>>()
    val postResult = MutableLiveData<String>()

    var id: Int = -1
        set(value) {
            field = value
            validate()
        }

    var title: String = ""
        set(value) {
            field = value
            validate()
        }

    var body: String = ""
        set(value) {
            field = value
            validate()
        }


    private fun validate() {

    }

    fun fetchPosts() {
        viewModelScope.launch {
            val response = repository.getPosts()
            if (response.isSuccessful) {
                obsResponse.postValue(response.body())
            } else {
                postResult.postValue("Error fetching posts.")
            }
        }
    }

    fun createPost() {
        viewModelScope.launch {
            val response = repository.createPost(title, body)
            if (response.isSuccessful) {
                postResult.postValue("Post created: ${response.body()}")
                fetchPosts()
            } else {
                postResult.postValue("Failed to create post.")
            }
        }
    }
}
