package com.cb.annopro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cb.anno.MyAnnotation

@MyAnnotation
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}