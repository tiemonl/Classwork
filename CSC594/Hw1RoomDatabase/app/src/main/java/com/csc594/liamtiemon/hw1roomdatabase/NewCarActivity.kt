package com.csc594.liamtiemon.hw1roomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewCarActivity : AppCompatActivity() {

    private lateinit var editCarView: EditText
    private lateinit var editCarMakeView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car)
        editCarView = findViewById(R.id.edit_car)
        editCarMakeView = findViewById(R.id.edit_car_make)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editCarView.text) || TextUtils.isEmpty(editCarMakeView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val car = editCarView.text.toString()
                val make = editCarMakeView.text.toString()
                replyIntent.putExtra("car", car)
                replyIntent.putExtra("make", make)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.carlistsql.REPLY"
    }
}