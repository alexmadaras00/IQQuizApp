package com.example.iqquizapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.Global.Companion.ageSelected
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.t
import com.example.iqquizapp.R
import kotlinx.android.synthetic.main.fragment_results.*


class ResultsFragment : Fragment(R.layout.fragment_results) {
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