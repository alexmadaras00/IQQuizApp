package com.example.iqquizapp

import android.os.Bundle
import android.system.Os.open
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iqquizapp.Adapters.AdapterList
import com.example.iqquizapp.DataClasses.Question
import com.example.iqquizapp.DataClasses.Test
import com.example.iqquizapp.Global.Companion.a
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.q2
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.DatagramChannel.open
import java.nio.channels.Selector.open
import java.nio.channels.SocketChannel.open


class ListFragment : Fragment(R.layout.fragment_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val array =JSONObject(getJSON()!!).getJSONArray("tests")
            println(array.length())
            for (i in 0 until array.length()) {
                val test = array.getJSONObject(i)
                val name = test.getString("name")
                val description = test.getString("description")
                val amountQuestion = test.getInt("amount_question")
                val questions = test.getJSONArray("questions")
                val testDetails = Test(name, description, 0, false, amountQuestion, q1, 0)
                t.add(testDetails)


            }
        } catch (e:JSONException) {

            e.printStackTrace()
        }


    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView.layoutManager=LinearLayoutManager(this.activity)
        recyclerView.adapter=AdapterList(t)
        recyclerView.isNestedScrollingEnabled=true

    }

    override fun onStart() {
        super.onStart()
        recyclerView.layoutManager=LinearLayoutManager(this.activity)
        recyclerView.adapter=AdapterList(t)

    }

    override fun onResume() {
        super.onResume()
        recyclerView.layoutManager=LinearLayoutManager(this.activity)
        recyclerView.adapter=AdapterList(t)
    }
    private fun getJSON(): String? {
        var json:String?=null
        val charset=Charsets.UTF_8
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