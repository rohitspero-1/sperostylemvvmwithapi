package com.example.a_to_z_apiretrofit_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.a_to_z_apiretrofit_mvvm.databinding.ActivityMainBinding
import com.example.a_to_z_apiretrofit_mvvm.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.obsResponse.observe(this) { list ->
            val builder = StringBuilder()
            for (item in list) {
                val obj = item.asJsonObject
                builder.append("• ${obj["title"].asString}\n")
            }
            binding.tvData.text = builder.toString()
        }

        viewModel.postResult.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.title = binding.editTitle.text.toString()
            viewModel.body = binding.editBody.text.toString()
            viewModel.createPost()
        }

        binding.btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }
    }
}
