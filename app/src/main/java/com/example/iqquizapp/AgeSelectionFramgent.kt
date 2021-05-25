package com.example.iqquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.Global.Companion.ageSelected
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_age_selection_framgent.*


class AgeSelectionFramgent : Fragment(R.layout.fragment_age_selection_framgent) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_age_selection_framgent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test_title.text=t[itemSelected].name
        println(itemSelected)
        println(test_title.text)
        arrow_2.setOnClickListener {
            if(age_button.text=="1-16")
            {
                age_button.text=">16"
                ageSelected=">16"
            }

        }
        arrow_1.setOnClickListener {
            if(age_button.text==">16") {
                age_button.text = "1-16"
                ageSelected="1-16"
            }
        }
        requireActivity().back_button.setOnClickListener {
            if(findNavController().currentDestination?.id==R.id.nav_age)
                findNavController().navigate(R.id.nav_list)
        }
        continue_button.setOnClickListener {
            findNavController().navigate(R.id.nav_question)
        }
    }

    override fun onStart() {
        super.onStart()
        test_title.text=t[itemSelected].name
    }

}