package com.example.iqquizapp.fragments

import Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iqquizapp.Adapters.AdapterList
import com.example.iqquizapp.Global
import com.example.iqquizapp.Global.Companion.added
import com.example.iqquizapp.Global.Companion.currentPointsTest1
import com.example.iqquizapp.Global.Companion.currentPointsTest2
import com.example.iqquizapp.Global.Companion.currentPointsTest3
import com.example.iqquizapp.Global.Companion.progressTest1
import com.example.iqquizapp.Global.Companion.progressTest2
import com.example.iqquizapp.Global.Companion.progressTest3
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.t
import com.example.iqquizapp.Global.Companion.test1Done
import com.example.iqquizapp.Global.Companion.test2Done
import com.example.iqquizapp.Global.Companion.test3Done
import com.example.iqquizapp.R
import com.example.iqquizapp.activities.MainActivity
import com.example.iqquizapp.models.Test
import com.example.iqquizapp.repository.database.User
import com.example.iqquizapp.repository.retrofit.INodeJS
import com.example.iqquizapp.repository.retrofit.RetrofitClient
import com.example.iqquizapp.ui.login.LoginResponse
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


class ListFragment : Fragment(R.layout.fragment_list) {
    var user: User? = null
    private lateinit var myAPI: INodeJS

    private val compositeDisposable = CompositeDisposable()
    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    override fun onStart() {
        super.onStart()
        val retrofit: Retrofit = RetrofitClient().getInstance()
        myAPI = retrofit.create(INodeJS::class.java)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.nav_list)
            }
        })
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if (isLoggedIn) {
            updateData()
            AdapterList(t).notifyDataSetChanged()
            AdapterList(t).addNewStatutes(t)
            user = Room.getInstance(this.requireContext()).user
        }
        print(user?.test1_progress)

        initialize()
        print(t)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterList(t)
        recyclerView.smoothScrollToPosition(0)
        AdapterList(t).addNewStatutes(t)
        recyclerView.isNestedScrollingEnabled = true
    }


    private fun initialize() {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterList(t)
        recyclerView.smoothScrollToPosition(0)
        AdapterList(t).notifyDataSetChanged()
        AdapterList(t).addNewStatutes(t)
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if (isLoggedIn)
            user = Room.getInstance(this.requireContext()).user
        getJSON()
        if (!added) {
            try {
                val array = JSONObject(getJSON()!!).getJSONArray("tests")
                println(array.length())
                q1.clear()
                t.clear()
                for (i in 0 until array.length()) {
                    val test = array.getJSONObject(i)
                    val name = test.getString("name")
                    val description = test.getString("description")
                    val amountQuestion = test.getInt("amount_question")

                    if (isLoggedIn) {
                        when (i) {
                            0 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        user?.test1_progress!!,
                                        user?.test1_done!!,
                                        amountQuestion,
                                        q1,
                                        user?.test1!!
                                    )
                                t.add(testDetails)
                            }
                            1 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        user?.test2_progress!! - 1,
                                        user?.test2_done!!,
                                        amountQuestion,
                                        q1,
                                        user?.test2!!
                                    )
                                t.add(testDetails)
                            }
                            2 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        user?.test3_progress!!,
                                        user?.test3_done!!,
                                        amountQuestion,
                                        q1,
                                        user?.test3!!
                                    )
                                t.add(testDetails)
                            }
                        }
                    } else {
                        when (i) {
                            0 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        progressTest1,
                                        test1Done,
                                        amountQuestion,
                                        q1,
                                        currentPointsTest1
                                    )
                                t.add(testDetails)
                            }
                            1 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        progressTest2,
                                        test2Done,
                                        amountQuestion,
                                        q1,
                                        currentPointsTest2
                                    )
                                t.add(testDetails)
                            }
                            2 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        progressTest3,
                                        test3Done,
                                        amountQuestion,
                                        q1,
                                        currentPointsTest3
                                    )
                                t.add(testDetails)
                            }
                        }
                    }
                }
            } catch (e: JSONException) {

                e.printStackTrace()
            }
            added = true
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.nav_list)
            }
        })
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if (isLoggedIn) {
            user = Room.getInstance(this.requireContext()).user
            print(user?.test1_progress)
            updateData()
            AdapterList(t).notifyDataSetChanged()
            AdapterList(t).addNewStatutes(t)
        }
        initialize()
        print(t)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterList(t)
        AdapterList(t).notifyDataSetChanged()
        recyclerView.smoothScrollToPosition(0)
        AdapterList(t).addNewStatutes(t)
        recyclerView.isNestedScrollingEnabled = true
    }


    private fun getJSON(): String? {
        val json: String?
        val charset = Charsets.UTF_8
        try {
            val jsonFile = context?.assets?.open("Tests.json")
            val size = jsonFile?.available()
            val buffer = ByteArray(size!!)
            jsonFile.read(buffer)
            jsonFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun updateData() {
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if (isLoggedIn)
            user = Room.getInstance(this.requireContext()).user

        myAPI.getDataUser(user?.id!!).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Room.getInstance(requireContext())
                        .saveUser(response.body()?.user!!)
                    if (Room.getInstance(requireContext()).user.username != "null") {

                        Global.isGoOffline = false
                    } else toast("Null username")
                } else if (response.code() == 401) toast("Session expired! Try again!")
                else toast("Could not retreive e-mail and password!")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    private fun toast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

}