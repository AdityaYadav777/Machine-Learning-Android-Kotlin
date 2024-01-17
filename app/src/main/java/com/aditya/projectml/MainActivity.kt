package com.aditya.projectml

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.projectml.databinding.ActivityMainBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class MainActivity : AppCompatActivity() {
    var data=ArrayList<myData>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamara.setOnClickListener {

            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager)!=null){

                startActivityForResult(intent,123)

            }else{
                Toast.makeText(this,"Somthing wrong",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123 && resultCode== RESULT_OK){
            val extras=data?.extras
            val bitmap=extras?.get("data") as Bitmap
            detectFace(bitmap)
        }
    }

    private fun detectFace(bitmap: Bitmap) {

        val Opts = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        val detector=FaceDetection.getClient(Opts)
        val image = InputImage.fromBitmap(bitmap, 0)

        val result = detector.process(image)
            .addOnSuccessListener { faces ->

                var resultText=""
                var i=1
                for(face in faces){
                    resultText="Face Number: $i"+
                            "\nSmile: ${face.smilingProbability?.times(100)}"+
                            "\nleft Eye open:${face.leftEyeOpenProbability?.times(100)}"+
                            "\nRight Eye open:${face.rightEyeOpenProbability?.times(100)}"+
                            "\nHead Euler:${face.headEulerAngleX}"

                    i++
                }
                if(faces.isEmpty()){
                    Toast.makeText(this,"Face Not Find",Toast.LENGTH_SHORT).show()
                }else{


                    binding.myRec.layoutManager=LinearLayoutManager(this)
                    data.add(myData(resultText,bitmap))
                    binding.myRec.adapter=myAdapter(data,this)


                }

            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

    }



}