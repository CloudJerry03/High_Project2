package com.example.high_project2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
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


        //asset파일 복사
        val adb=AssetDatabaseOpenHelper(this)
        adb.openDatabase()

        val sqliteHelper=SqliteHelper(this,"data",1)

        var data=sqliteHelper.selectData()

        binding.webView.addJavascriptInterface(WebAppInterface(this,data,binding), "Android")
        //Log.d("checkfor",data[0].name)


        //테스트용
//        val cl=WebAppInterface(this,data,binding)
//        cl.sendData("신문지")

        //null에 toString하면 "null"이 나오나 ""이 나오나 ->null이 나오네
//        var nu:String?=null
//        Log.d("checkfor",nu.toString()+"hello")

    }

}

class WebAppInterface(private val context: Context,val list: MutableList<Data>,val binding: ActivityMainBinding){
    //private lateinit var binding:ActivityMainBinding
    //안드로이드->웹뷰 데이터 전달
    @JavascriptInterface
    fun sendData(name:String){
        Log.d("checkfor",name)
        var msg:String
        //데이터 검색
        for(i in 0..list.size-1){
            if(name==list[i].name){
                Log.d("checkfor","${list[i].toString()}")
                msg="${list[i].name}:${list[i].category}:${list[i].category2}"
                //binding.webView.loadUrl("javascript:~~~~('"+msg+"')")
                Log.d("checkfor",msg)
                Toast.makeText(context, msg , Toast.LENGTH_SHORT).show()
                break
            }

        }
    }
}