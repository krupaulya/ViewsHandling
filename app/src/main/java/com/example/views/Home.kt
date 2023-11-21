package com.example.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val withLibrary: Button = view.findViewById(R.id.with)
        val withoutLibrary: Button = view.findViewById(R.id.without)
        withLibrary.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home2_to_imagesWithLibrary)
        }
        withoutLibrary.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home2_to_imagesWithoutLibrary)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            Home().apply {
            }
    }
}