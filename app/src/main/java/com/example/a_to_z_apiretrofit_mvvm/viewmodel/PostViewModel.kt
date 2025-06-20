package com.example.a_to_z_apiretrofit_mvvm.viewmodel

import android.util.Log
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
    val postResultMedicine = MutableLiveData<String>()

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

    var name: String = ""
        set(value) {
            field = value
            validate()
        }

    var Gender: String = ""
        set(value) {
            field = value
            validate()
        }

    var address: String = ""
        set(value) {
            field = value
            validate()
        }

    var MedicineName: String = ""
    var medicineQuantity: Int = 0


    private fun validate() {
        if (title.isBlank()) {
            Log.e("Tag", "Title is Empty")
        }
        if (name.isEmpty()) {
            Log.e("TAG", "Name is Empty")
        }
    }


    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = repository.getPosts()
                if (response.isSuccessful) {
                    obsResponse.postValue(response.body())
                } else {
                    postResult.postValue("Error fetching posts: ${response.code()}")
                }
            } catch (e: Exception) {
                postResult.postValue("Network error: ${e.message}")
            }
        }
    }

    fun createPost() {
        viewModelScope.launch {
            try {
                val response = repository.createPost(title, name, Gender, address)
                if (response.isSuccessful) {
                    postResult.postValue("Post created successfully")
                } else {
                    postResult.postValue("Failed to create post: ${response.code()}")
                }
            } catch (e: Exception) {
                postResult.postValue("Error: ${e.message}")
            }
        }
    }

    fun createMedicinePostData() {
        viewModelScope.launch {
            try {
                val response = repository.createPostMedicineData(MedicineName, medicineQuantity)
                if (response.isSuccessful) {
                    postResultMedicine.postValue("Medicine data created successfully")
                } else {
                    postResultMedicine.postValue("Failed to create medicine data")
                }
            } catch (e: Exception) {
                postResultMedicine.postValue("Error: ${e.message}")
            }
        }
    }
}