package com.example.high_project2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.high_project2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.web=this@MainActivity


        binding.webView.settings.javaScriptEnabled=true
        binding.webView.setWebViewClient(object: WebViewClient(){})

        //asset db파일 불러오기
        val adb=AssetDatabaseOpenHelper(this)
        adb.openDatabase()



    }
}