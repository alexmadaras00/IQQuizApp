package com.example.iqquizapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.iqquizapp.DataClasses.Test
import com.example.iqquizapp.Global
import com.example.iqquizapp.R
import kotlinx.android.synthetic.main.card_test.view.*

class AdapterList(val t: ArrayList<Test>):RecyclerView.Adapter<AdapterList.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val view= LayoutInflater.from(parent.context.applicationContext).inflate(R.layout.card_test,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        holder.apply {
            itemView.title.text=t[position].name
            itemView.test_description.text=t[position].description
            itemView.progress_amount.text="${t[position].currentProgress}/${t[position].amountQuestions}"
            itemView.select_button.setOnClickListener {
                if(Global.t[position].done)
                    Toast.makeText(holder.itemView.context,"Test already done", Toast.LENGTH_SHORT).show()
                else {

                    findNavController(itemView).navigate(R.id.nav_age)
                    Global.itemSelected = position
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return t.size
    }
}