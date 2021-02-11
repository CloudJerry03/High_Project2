package com.example.high_project2

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AssetDatabaseOpenHelper(private val context: Context) {

    companion object {

        private val DB_NAME = "data.db"

    }

    fun openDatabase(): SQLiteDatabase {

        val dbFile = context.getDatabasePath(DB_NAME)
        Log.d("#DB",dbFile.path)
        Log.d("#DB",dbFile.exists().toString())

        if (!dbFile.exists()) {
            try {
                val checkDB = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null)

                checkDB?.close()
                copyDatabase(dbFile)
            } catch (e: IOException) {
                throw RuntimeException("Error creating source database", e)
            }
            Log.d("#DB","not exist")

        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)

    }

    @SuppressLint("WrongConstant")
    private fun copyDatabase(dbFile: File) {
        val `is` = context.assets.open(DB_NAME)
        Log.d("#DB","input stream : ${`is`.toString()}")
        val os = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        while (`is`.read(buffer) > 0) {
            os.write(buffer)
            Log.d("#DB", "writing>>")
        }

        os.flush()
        os.close()
        `is`.close()
        Log.d("#DB", "copy completed..")
    }
}