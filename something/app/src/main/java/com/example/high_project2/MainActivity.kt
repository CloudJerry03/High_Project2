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
        binding.webView.loadUrl("file:///android_asset/index.html")
        binding.webView.settings.javaScriptEnabled=true
        binding.webView.setWebChromeClient(object: WebChromeClient(){})
        binding.webView.setWebViewClient(object: WebViewClient(){})

        //asset db파일 불러오기
        val adb=AssetDatabaseOpenHelper(this)
        adb.openDatabase()

        //DB파일에서 list가져오기
        val helper=SqliteHelper(this,"data",1)


        var data=helper.selectData()
        Log.d("checkfor",data[0].name)








        //원하는 데이터 전달




    }
    //안드로이드->웹뷰 데이터 전달
    @JavascriptInterface
    fun sendData(item:String){


        var msg:String //->item에 해당하는 항목 데이터 할당
        //binding.webView.loadUrl("javascript:~~~~('"+msg+"')")
    }

    //웹뷰 데이터 요청 받기
    @JavascriptInterface
    fun recieveData(item:String){
        sendData(item)
    }

    //아이템검사
}