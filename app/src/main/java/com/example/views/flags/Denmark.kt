package com.example.views.flags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.views.R

class Denmark : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_denmark, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = Denmark().apply {

        }
    }
}