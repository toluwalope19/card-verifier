package com.example.card_info_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.card_info_finder.databinding.ActivityMainBinding
import com.example.card_info_finder.di.ViewModelProviderFactory
import com.example.card_info_finder.model.ApiResponse
import com.example.card_info_finder.util.Resource
import com.example.card_info_finder.util.Status
import com.example.card_info_finder.viewmodels.CardViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: CardViewModel

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory).get(CardViewModel::class.java)
        viewModel.getDetails()
        setUpObserver()
    }


    private fun setUpObserver(){
        viewModel.response.observe(this, Observer{
            it.let {resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        Log.i("yeeeah",resource.data.toString())
                        resource.data
                    }
                    Status.ERROR-> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING ->{
                        Toast.makeText(this, "loading", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}