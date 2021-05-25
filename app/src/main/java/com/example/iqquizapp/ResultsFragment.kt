package com.example.iqquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.Global.Companion.ageSelected
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.fragment_results.*


class ResultsFragment : Fragment(R.layout.fragment_results) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_results, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result_amount.text=t[itemSelected].points.toString()
        "Total Answers: ${t[itemSelected].amountQuestions}".also { total_answers.text = it }
        "Age Selected: $ageSelected".also { age_selected.text = it }
        continue_button3.setOnClickListener {
            findNavController().navigate(R.id.nav_list)
        }

    }

}