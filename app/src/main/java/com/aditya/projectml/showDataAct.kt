package com.aditya.projectml

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.projectml.databinding.ActivityShowDataBinding

class showDataAct : AppCompatActivity() {
    lateinit var binding:ActivityShowDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val img: Bitmap? =intent.getParcelableExtra("img")
        val text=intent.getStringExtra("text")

        binding.myPhoto.setImageBitmap(img)
        binding.myData.text=text

    }
}