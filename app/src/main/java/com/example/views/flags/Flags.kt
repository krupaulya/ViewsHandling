package com.example.views.flags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.views.R

class Flags : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val austria : TextView = view.findViewById(R.id.austria)
        val poland : TextView = view.findViewById(R.id.poland)
        val italy : TextView = view.findViewById(R.id.italy)
        val colombia : TextView = view.findViewById(R.id.colombia)
        val madagascar : TextView = view.findViewById(R.id.madagascar)
        val thailand : TextView = view.findViewById(R.id.thailand)
        val denmark : TextView = view.findViewById(R.id.denmark)
        val switzerland : TextView = view.findViewById(R.id.switzerland)
        austria.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_austria2)
        }
        poland.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_poland2)
        }
        italy.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_italy2)
        }
        colombia.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_colombia2)
        }
        madagascar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_madagascar2)
        }
        thailand.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_thailand2)
        }
        denmark.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_denmark2)
        }
        switzerland.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_flags2_to_switzerland2)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Flags().apply {
                arguments = Bundle().apply {

                }
            }
    }
}