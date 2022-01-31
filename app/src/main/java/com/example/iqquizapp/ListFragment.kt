package com.example.iqquizapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iqquizapp.Adapters.AdapterList
import com.example.iqquizapp.DataClasses.Test
import com.example.iqquizapp.Global.Companion.added
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class ListFragment : Fragment(R.layout.fragment_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    val testDetails = Test(name, description, 0, false, amountQuestion, q1, 0)
                    t.add(testDetails)


                }
            } catch (e: JSONException) {

                e.printStackTrace()
            }
            added = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onStart() {
        super.onStart()
        recyclerView.layoutManager=LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterList(t)
        recyclerView.isNestedScrollingEnabled = true

    }

    override fun onResume() {
        super.onResume()
    }
    private fun getJSON(): String? {
        val json: String?
        val charset = Charsets.UTF_8
        try{
            val jsonFile=context?.assets?.open("Tests.json")
            val size=jsonFile?.available()
            val buffer= ByteArray(size!!)
            jsonFile.read(buffer)
            jsonFile.close()
            json= String(buffer,charset)
        }catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }
}