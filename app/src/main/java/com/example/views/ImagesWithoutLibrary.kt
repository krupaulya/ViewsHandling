package com.example.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.util.concurrent.Executors

class ImagesWithoutLibrary : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images_without_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val input: EditText = view.findViewById(R.id.input_wt_l)
        val image: ImageView = view.findViewById(R.id.image_wt_l)
        val load: Button = view.findViewById(R.id.load_wt_l)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image1: Bitmap?


        load.setOnClickListener {
            val inputValue = input.text.toString()
            executor.execute {
                try {
                    val `in` = java.net.URL(inputValue).openStream()
                    image1 = BitmapFactory.decodeStream(`in`)

                    handler.post {
                        image.setImageBitmap(image1)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ImagesWithoutLibrary().apply {
            }
    }
}