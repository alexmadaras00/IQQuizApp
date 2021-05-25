package com.example.iqquizapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.DataClasses.Question
import com.example.iqquizapp.DataClasses.Test
import com.example.iqquizapp.Global.Companion.a
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.p1
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_question.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class QuestionFragment : Fragment(R.layout.fragment_question) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        "${q1[t[itemSelected].currentProgress].number?.plus(1)}/${t[itemSelected].amountQuestions}".also { progress_questions.text = it }
        question_text.text=q1[t[itemSelected].currentProgress].question
        question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
        println(q1[t[itemSelected].currentProgress])
        if(q1[t[itemSelected].currentProgress].type!="boolean") {
            answer_1.text = q1[t[itemSelected].currentProgress].a1
            answer_2.text = q1[t[itemSelected].currentProgress].a2
            answer_3.text = q1[t[itemSelected].currentProgress].a3
            answer_4.text = q1[t[itemSelected].currentProgress].a4
            answer_5.text = q1[t[itemSelected].currentProgress].a5
            answer_6.text = q1[t[itemSelected].currentProgress].a6
            answer_1.setOnClickListener {
                answer_1.setTextColor(Color.parseColor("#E9980B"))
                answer_6.setTextColor(Color.WHITE)
                answer_5.setTextColor(Color.WHITE)
                answer_2.setTextColor(Color.WHITE)
                answer_3.setTextColor(Color.WHITE)
                answer_4.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_1.text.toString()
            }
            answer_2.setOnClickListener {
                answer_2.setTextColor(Color.parseColor("#E9980B"))
                answer_6.setTextColor(Color.WHITE)
                answer_5.setTextColor(Color.WHITE)
                answer_1.setTextColor(Color.WHITE)
                answer_3.setTextColor(Color.WHITE)
                answer_4.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_2.text.toString()
            }
            answer_3.setOnClickListener {
                answer_3.setTextColor(Color.parseColor("#E9980B"))
                answer_6.setTextColor(Color.WHITE)
                answer_5.setTextColor(Color.WHITE)
                answer_2.setTextColor(Color.WHITE)
                answer_1.setTextColor(Color.WHITE)
                answer_4.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_3.text.toString()
            }
            answer_4.setOnClickListener {
                answer_4.setTextColor(Color.parseColor("#E9980B"))
                answer_2.setTextColor(Color.WHITE)
                answer_6.setTextColor(Color.WHITE)
                answer_5.setTextColor(Color.WHITE)
                answer_3.setTextColor(Color.WHITE)
                answer_1.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_4.text.toString()
            }
            answer_5.setOnClickListener {
                answer_5.setTextColor(Color.parseColor("#E9980B"))
                answer_4.setTextColor(Color.WHITE)
                answer_6.setTextColor(Color.WHITE)
                answer_2.setTextColor(Color.WHITE)
                answer_3.setTextColor(Color.WHITE)
                answer_1.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_4.text.toString()
            }
            answer_6.setOnClickListener {
                answer_6.setTextColor(Color.parseColor("#E9980B"))
                answer_5.setTextColor(Color.WHITE)
                answer_4.setTextColor(Color.WHITE)
                answer_2.setTextColor(Color.WHITE)
                answer_3.setTextColor(Color.WHITE)
                answer_1.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_4.text.toString()
            }
        }
        else{
            answer_1.text = q1[t[itemSelected].currentProgress].a1
            answer_2.text = q1[t[itemSelected].currentProgress].a2
            answer_3.visibility=View.INVISIBLE
            answer_4.visibility=View.INVISIBLE
            answer_5.visibility=View.INVISIBLE
            answer_6.visibility=View.INVISIBLE
            answer_1.setOnClickListener {
                answer_1.setTextColor(Color.parseColor("#E9980B"))
                answer_2.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_1.text.toString()
            }
            answer_2.setOnClickListener {
                answer_2.setTextColor(Color.parseColor("#E9980B"))
                answer_1.setTextColor(Color.WHITE)
                q1[t[itemSelected].currentProgress].answerUser=answer_2.text.toString()
            }
        }

        continue_button2.setOnClickListener {
            if (q1[t[itemSelected].currentProgress].answerUser == null)
                Toast.makeText(
                    this.activity,
                    "Please choose one of the answers.",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                    if (q1[t[itemSelected].currentProgress].number == t[itemSelected].amountQuestions) {

                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a1) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a2) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a2_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a3) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a3_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a4) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a4_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a5) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a5_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a6) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a6_weight
                        }
                        t[itemSelected].currentProgress = t[itemSelected].amountQuestions
                        t[itemSelected].done = true

                        println(t[itemSelected].points)
                        findNavController().navigate(R.id.nav_results)
                    } else {
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a1) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a2) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a2_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a3) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a3_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a4) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a4_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a5) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a5_weight
                        }
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a6) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a6_weight
                        }
                        q1[t[itemSelected].currentProgress].number =
                            q1[t[itemSelected].currentProgress].number?.plus(1)
                        t[itemSelected].currentProgress++

                        "${q1[t[itemSelected].currentProgress].number}/${t[itemSelected].amountQuestions}".also { progress_questions.text = it }
                        if (q1[t[itemSelected].currentProgress].type != "multiplechoice")
                        {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.visibility=View.INVISIBLE
                            answer_4.visibility=View.INVISIBLE
                            answer_5.visibility=View.INVISIBLE
                            answer_6.visibility=View.INVISIBLE
                        }
                        else
                        {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer_3.visibility=View.VISIBLE
                            answer_4.visibility=View.VISIBLE
                            answer_5.visibility=View.VISIBLE
                            answer_6.visibility=View.VISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_5.setTextColor(Color.WHITE)
                            answer_6.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4
                            answer_5.text = q1[t[itemSelected].currentProgress].a5
                            answer_6.text = q1[t[itemSelected].currentProgress].a6

                        }

                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
    }
    private fun initialize()
    {
        try {
            val array = JSONObject(getJSON()!!).getJSONArray("tests")
            println(array.length())
            for (i in 0 until array.length()) {

                val test = array.getJSONObject(i)
                val name = test.getString("name")
                val description = test.getString("description")
                val amountQuestion = test.getInt("amount_question")
                val questions = test.getJSONArray("questions")
                if(itemSelected==i) {
                    for (j in 0 until questions.length()) {
                        val question = questions.getJSONObject(j)
                        val text = question.getString("text")
                        val type = question.getString("type")
                        val a1 = question.getString("r1")
                        val a2 = question.getString("r2")
                        val a1Points = question.getInt("weight-r1")
                        val a2Points = question.getInt("weight-r2")
                        if (type == "boolean") {
                            val questionDetails = Question(
                                text,
                                type,
                                a1Points,
                                a2Points,
                                0,
                                0,
                                0,
                                0,
                                a1,
                                a2,
                                null,
                                null,
                                null,
                                null,
                                i,
                                R.drawable.draw,
                                null
                            )
                            q1.add(questionDetails)
                        } else {
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a5 = question.getString("r5")
                            val a6 = question.getString("r6")
                            val a3Points = question.getInt("weight-r3")
                            val a4Points = question.getInt("weight-r4")
                            val a5Points = question.getInt("weight-r5")
                            val a6Points = question.getInt("weight-r6")
                            val questionDetails = Question(
                                text,
                                type,
                                a1Points,
                                a2Points,
                                a3Points,
                                a4Points,
                                a5Points,
                                a6Points,
                                a1,
                                a2,
                                a3,
                                a4,
                                a5,
                                a6,
                                i+1,
                                R.drawable.draw,
                                null
                            )
                            q1.add(questionDetails)
                        }
                    }
                }
                break
            }
        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
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