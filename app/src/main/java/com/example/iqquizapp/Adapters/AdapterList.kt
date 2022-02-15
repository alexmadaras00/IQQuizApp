package com.example.iqquizapp.Adapters

import Room
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.iqquizapp.Global
import com.example.iqquizapp.R
import com.example.iqquizapp.models.Test
import com.example.iqquizapp.repository.database.User
import com.example.iqquizapp.repository.retrofit.INodeJS
import com.example.iqquizapp.repository.retrofit.RetrofitClient
import com.example.iqquizapp.ui.login.LoginResponse
import kotlinx.android.synthetic.main.card_test.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*

class AdapterList(var t: ArrayList<Test>) : RecyclerView.Adapter<AdapterList.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext)
            .inflate(R.layout.card_test, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        var user: User? = null
        val v: View = holder.itemView
        val context = v.context
        val isLoggedIn = Room.getInstance(context).isLoggedIn
        if (isLoggedIn) {
            user = Room.getInstance(context).user
            updateData(user, context)
        }
        holder.apply {

            itemView.title.text = t[position].name
            itemView.test_description.text = t[position].description
            if (isLoggedIn) {
                when (position) {
                    0 -> itemView.progress_amount.text =
                        "${user?.test1_progress}/${t[position].amountQuestions}"
                    1 -> itemView.progress_amount.text =
                        "${user?.test2_progress}/${t[position].amountQuestions}"
                    2 -> itemView.progress_amount.text =
                        "${user?.test3_progress}/${t[position].amountQuestions}"

                }
            } else
                itemView.progress_amount.text =
                    "${t[position].currentProgress}/${t[position].amountQuestions}"
            itemView.select_button.setOnClickListener {
                if (Global.t[position].done)
                    Toast.makeText(holder.itemView.context, "Test already done", Toast.LENGTH_SHORT)
                        .show()
                else {
                    if (Global.t[position].currentProgress > 0 && !Global.t[position].done)
                        findNavController(itemView).navigate(R.id.nav_question)
                    else findNavController(itemView).navigate(R.id.nav_age)
                    Global.itemSelected = position
                }
            }

        }

    }

    override fun getItemCount(): Int {
        return t.size
    }

    private fun updateData(user: User, context: Context) {

        lateinit var myAPI: INodeJS
        val retrofit: Retrofit = RetrofitClient().getInstance()
        myAPI = retrofit.create(INodeJS::class.java)
        myAPI.getDataUser(user.id).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Room.getInstance(context)
                        .saveUser(response.body()?.user!!)
                    if (Room.getInstance(context).user.username != "null") {

                        Global.isGoOffline = false
                    } else toast("Null username", context)
                } else if (response.code() == 401) toast("Session expired! Try again!", context)
                else toast("Could not retreive e-mail and password!", context)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    private fun toast(s: String, context: Context) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
    fun addNewStatutes(tests: ArrayList<Test>) {
        tests.addAll(tests)
        notifyDataSetChanged()
    }

}