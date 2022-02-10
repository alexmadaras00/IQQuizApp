package com.example.iqquizapp.fragments

import Room
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iqquizapp.Adapters.AdapterList
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
import com.example.iqquizapp.models.Test
import com.example.iqquizapp.models.User
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class ListFragment : Fragment(R.layout.fragment_list) {
    var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if(isLoggedIn)
             user = Room.getInstance(this.requireContext()).user
        print(user?.test1_progress)
        getJSON()
        if (!added) {
            try {
                val array = JSONObject(getJSON()!!).getJSONArray("tests")
                println(array.length())
                q1.clear()
                for (i in 0 until array.length()) {
                    val test = array.getJSONObject(i)
                    val name = test.getString("name")
                    val description = test.getString("description")
                    val amountQuestion = test.getInt("amount_question")
                    val questions = test.getJSONArray("questions")
                    if (isLoggedIn) {
                        when (i) {
                            0 -> {
                                val testDetails =
                                    Test(
                                        name,
                                        description,
                                        user?.test1_progress!!,
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
                                        user?.test2_progress!!,
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
                                        user?.test3_progress!!,
                                        test3Done,
                                        amountQuestion,
                                        q1,
                                        currentPointsTest3
                                    )
                                t.add(testDetails)
                            }
                        }
                    }
                    else{
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


    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterList(t)
        recyclerView.isNestedScrollingEnabled = true

    }

    override fun onResume() {
        super.onResume()
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

}