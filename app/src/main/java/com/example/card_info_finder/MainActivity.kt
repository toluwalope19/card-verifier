package com.example.card_info_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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
        binding.cardNumber.addTextChangedListener(cadTextWatcher)
        viewModel = ViewModelProvider(this, factory).get(CardViewModel::class.java)
        setUpListeners()
        setUpObserver()
    }


    private fun setUpObserver(){
        viewModel.response.observe(this, Observer{
            it.let {resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        Log.i("yeeeah",resource.data.toString())
                        resource.data
                        binding.progressBar.visibility=  View.GONE
                        binding.checkCard.text = getString(R.string.check)
                    }
                    Status.ERROR-> {
                        binding.checkCard.text = getString(R.string.check)
                        binding.progressBar.visibility=  View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING ->{
                        binding.progressBar.translationZ = 42F
                        binding.checkCard.text = getString(R.string.checking)
                        binding.progressBar.visibility=  View.VISIBLE
                        Toast.makeText(this, "loading", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }



    private val cadTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = binding.cardNumber.text.toString()
            binding.checkCard.isEnabled = text.length >= 6
            binding.clearButton.visibility =View.VISIBLE
        }
        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun setUpListeners(){

        binding.checkCard.setOnClickListener {
            val number = binding.cardNumber.text.toString()
            viewModel.getDetails(number.toInt())
        }

        binding.clearButton.setOnClickListener {
            binding.cardNumber.setText("")
        }
    }
}