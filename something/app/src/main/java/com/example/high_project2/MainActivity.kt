package com.example.high_project2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
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
        binding.webView.setWebChromeClient(object: WebChromeClient(){})
        binding.webView.setWebViewClient(object: WebViewClient(){})
        TODO("url 작성")
        binding.webView.loadUrl("file:///android_asset/index.html")

        //asset db파일 불러오기
        val adb=AssetDatabaseOpenHelper(this)
        adb.openDatabase()


        //원하는 데이터 전달




    }
    //안드로이드->웹뷰 데이터 전달
    @JavascriptInterface
    fun android_to_js(msg:String){
        TODO("loadUrl작성성")
        binding.webView.loadUrl("javascript:~~~~('"+msg+"')")
    }
}