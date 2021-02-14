package com.example.high_project2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.webkit.JavascriptInterface
import org.json.JSONArray

class SqliteHelper(context: Context,name: String, version:Int):SQLiteOpenHelper(context,name,null,version) {

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



    @JavascriptInterface
    fun selectData():MutableList<Data>{

        val list= mutableListOf<Data>()
        val select="select * from data"
        val rd=readableDatabase
//ERR
        val cursor=rd.rawQuery(select,null)     //->여기서 에러 생기면 두번중 두번은 파일 문제였음 / DB 파일 싹삭제하고 다시 넣어보고, asset에 DB이름에 확장자 빼고 다시 넣어보기

        while(cursor.moveToNext()){
            val no=cursor.getLong(cursor.getColumnIndex("no"))
            val name=cursor.getString(cursor.getColumnIndex("name"))
            val category=cursor.getString(cursor.getColumnIndex("category"))
            val category2=cursor.getString(cursor.getColumnIndex("category2"))
            val catnum=cursor.getLong(cursor.getColumnIndex("catnum"))
            val catnum2=cursor.getLong(cursor.getColumnIndex("catnum2"))

            list.add(Data(no,name,category,catnum,category2,catnum2))
        }

        cursor.close()
        rd.close()
        return list
    }
}

data class Data(var no:Long,var name:String,var category:String,var catnum:Long,var category2:String?,var catnum2:Long?){

}