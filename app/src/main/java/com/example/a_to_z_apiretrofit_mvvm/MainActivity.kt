package com.example.a_to_z_apiretrofit_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.a_to_z_apiretrofit_mvvm.databinding.ActivityMainBinding
import com.example.a_to_z_apiretrofit_mvvm.viewmodel.PostViewModel
import com.ncorti.slidetoact.SlideToActView

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


        val slideView = findViewById<SlideToActView>(R.id.btnSubmit)
        slideView.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {

                viewModel.title = binding.editTitle.text.toString()
                viewModel.body = binding.editBody.text.toString()
                viewModel.createPost()
            }
        }

        val slidebtn = findViewById<SlideToActView>(R.id.btnFetch)
        slidebtn.onSlideCompleteListener = object  : SlideToActView.OnSlideCompleteListener{
            override fun onSlideComplete(view: SlideToActView) {
                viewModel.fetchPosts()
            }
        }
    }
}
