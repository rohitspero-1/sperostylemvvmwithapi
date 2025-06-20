package com.example.a_to_z_apiretrofit_mvvm

import android.os.Bundle
import android.widget.RadioButton
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
            list?.let {
                val builder = StringBuilder()
                for (item in it) {
                    val obj = item.asJsonObject
                    builder.append("• ${obj["title"].asString}\n")
                }
                binding.tvData.text = builder.toString()
            }
        }

        viewModel.postResult.observe(this) { resultMessage ->
            resultMessage?.let {
                binding.tvFeachedData.text = it
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.postResultMedicine.observe(this) { resultMessage ->
            resultMessage?.let {
                binding.tvFeachedMedicineData.text = it
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSubmit.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                viewModel.title = binding.editTitle.text.toString()
                viewModel.name = binding.editName.text.toString()
                viewModel.Gender = getSelectedGender()
                viewModel.address = binding.editAddress.text.toString()

                viewModel.MedicineName = binding.editMedicineName.text.toString()
                viewModel.medicineQuantity = binding.editMedicineQuntity.text.toString().toIntOrNull() ?: 0

                viewModel.createPost()
                viewModel.createMedicinePostData()
            }
        }

        binding.btnFetch.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                viewModel.fetchPosts()
            }
        }
    }

    private fun getSelectedGender(): String {
        return findViewById<RadioButton>(binding.radioGroupGender.checkedRadioButtonId)?.text?.toString() ?: ""
    }
}