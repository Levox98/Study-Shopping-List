package com.levox.studyshoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.levox.studyshoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shopList.observe(this) {
            Log.d("TEST", it.toString())
        }

        viewModel.getShopList()
    }
}