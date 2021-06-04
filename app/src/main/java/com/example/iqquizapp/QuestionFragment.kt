package com.example.iqquizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.DataClasses.Question
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.t
import kotlinx.android.synthetic.main.fragment_question.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class QuestionFragment : Fragment(R.layout.fragment_question) {

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        q1.clear()
        initialize()
        println("size: ${q1.size}")
        println(itemSelected)
        println(q1.size)
        println(q1[t[itemSelected].currentProgress].number)
        println(t[itemSelected].currentProgress)


        when (q1[t[itemSelected].currentProgress].type) {
            "onechoice" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.VISIBLE
                answer_7.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                }
            }
            "onechoice-4" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.INVISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
            }
            "onechoice-5" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_5.text = q1[t[itemSelected].currentProgress].a5
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
            }
            "onechoice-7" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.VISIBLE
                answer_7.visibility = View.VISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_5.text = q1[t[itemSelected].currentProgress].a5
                answer_6.text = q1[t[itemSelected].currentProgress].a6
                answer_7.text = q1[t[itemSelected].currentProgress].a7
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                }
                answer_7.setOnClickListener {
                    answer_7.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_7.text.toString()
                }
            }
            "free" -> {
                type_answer.visibility = View.VISIBLE
                answer.visibility = View.VISIBLE
                answer_1.visibility = View.INVISIBLE
                answer_2.visibility = View.INVISIBLE
                answer_3.visibility = View.INVISIBLE
                answer_4.visibility = View.INVISIBLE
                answer_5.visibility = View.INVISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE

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
                if (t[itemSelected].currentProgress == t[itemSelected].amountQuestions) {
                    if (q1[t[itemSelected].currentProgress].type != "free") {
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
                        if (q1[t[itemSelected].currentProgress].answerUser!!.equals(q1[t[itemSelected].currentProgress].a1, ignoreCase = true)) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        } else t[itemSelected].points += 0
                    }
                } else {
                    if (q1[t[itemSelected].currentProgress].type != "free") {
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
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a7) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a7_weight
                        }
                    } else {
                        q1[t[itemSelected].currentProgress].answerUser = answer.editText?.text.toString().toLowerCase(Locale.getDefault())
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a1?.toLowerCase(Locale.getDefault()))
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        else t[itemSelected].points += 0
                    }
                    q1[t[itemSelected].currentProgress].number = q1[t[itemSelected].currentProgress].number?.plus(1)
                    t[itemSelected].currentProgress++

                    "${q1[t[itemSelected].currentProgress].number}/${t[itemSelected].amountQuestions}".also { progress_questions.text = it }
                    println(t[itemSelected].currentProgress)
                    when (q1[t[itemSelected].currentProgress].type) {
                        "onechoice-4" -> {
                            println(t[itemSelected].currentProgress)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.INVISIBLE
                            answer_6.visibility = View.INVISIBLE
                            answer_7.visibility = View.INVISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4

                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                        }
                        "onechoice" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.VISIBLE
                            answer_7.visibility = View.INVISIBLE
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
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }
                            answer_6.setOnClickListener {
                                answer_6.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                            }
                        }
                        "onechoice-5" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.INVISIBLE
                            answer_7.visibility = View.INVISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_5.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4
                            answer_5.text = q1[t[itemSelected].currentProgress].a5
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }

                        }
                        "onechoice-7" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.VISIBLE
                            answer_7.visibility = View.VISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_5.setTextColor(Color.WHITE)
                            answer_6.setTextColor(Color.WHITE)
                            answer_7.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4
                            answer_5.text = q1[t[itemSelected].currentProgress].a5
                            answer_6.text = q1[t[itemSelected].currentProgress].a6
                            answer_7.text = q1[t[itemSelected].currentProgress].a7
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }
                            answer_6.setOnClickListener {
                                answer_6.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                            }
                            answer_7.setOnClickListener {
                                answer_7.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_7.text.toString()
                            }
                        }
                    }
                }
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initialize() = try {
        val array = JSONObject(getJSON()!!).getJSONArray("tests")
        println(array.length())
        for (i in 0 until array.length()) {
            val test = array.getJSONObject(i)
            val name = test.getString("name")
            test.getString("description")
            val amountQuestion = test.getInt("amount_question")
            val questions = test.getJSONArray("questions")
            println(itemSelected)
            if (itemSelected == i) {
                for (j in 0 until questions.length()) {
                    println("size: ${q1.size}")
                    val question = questions.getJSONObject(j)
                    val text = question.getString("text")
                    when (val type = question.getString("type")) {
                        "onechoice-4" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight-r1")
                            val a2Points = question.getInt("weight-r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight-r3")
                            val a4Points = question.getInt("weight-r4")
                            if (question.getString("image") != "null") {
                                val image = question.getString("image")
                                val resourceId = resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        0,
                                        0,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        null,
                                        null,
                                        null,
                                        j + 1,
                                        resourceId,
                                        null
                                )
                                q1.add(questionDetails)
                            } else {
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        0,
                                        0,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        null,
                                        null,
                                        null,
                                        j + 1,
                                        0,
                                        null
                                )
                                q1.add(questionDetails)
                            }

                        }
                        "onechoice-5" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight-r1")
                            val a2Points = question.getInt("weight-r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight-r3")
                            val a4Points = question.getInt("weight-r4")
                            val a5 = question.getString("r5")
                            val a5Points = question.getInt("weight-r5")
                            if (question.getString("image") != "null") {
                                val image = question.getString("image")
                                val resourceId = resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        0,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5,
                                        null,
                                        null,
                                        j + 1,
                                        resourceId,
                                        null
                                )
                                q1.add(questionDetails)
                            } else {
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        0,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5,
                                        null,
                                        null,
                                        j + 1,
                                        0,
                                        null
                                )
                                q1.add(questionDetails)
                            }
                        }
                        "onechoice-7" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight-r1")
                            val a2Points = question.getInt("weight-r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight-r3")
                            val a4Points = question.getInt("weight-r4")
                            val a5 = question.getString("r5")
                            val a5Points = question.getInt("weight-r5")
                            val a6 = question.getString("r6")
                            val a6Points = question.getInt("weight-r6")
                            val a7 = question.getString("r7")
                            val a7Points = question.getInt("weight-r7")
                            if (question.getString("image") != "null") {
                                val image = question.getString("image")
                                val resourceId = resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        a6Points,
                                        a7Points,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5,
                                        a6, a7,
                                        j + 1,
                                        resourceId,
                                        null
                                )
                                q1.add(questionDetails)
                            } else {
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        a6Points,
                                        a7Points,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5,
                                        a6, a7,
                                        j + 1,
                                        0,
                                        null
                                )
                                q1.add(questionDetails)
                            }

                        }
                        "onechoice" -> {
                            val question = questions.getJSONObject(j)
                            val text = question.getString("text")
                            var a1Points = 0
                            var a2Points = 0
                            var a3Points = 0
                            var a4Points = 0
                            var a5Points = 0
                            var a6Points = 0
                            val a1: String = question.getString("r1")
                            val a2: String = question.getString("r2")
                            val a3: String = question.getString("r3")
                            val a4: String = question.getString("r4")
                            val a5: String = question.getString("r5")
                            val a6: String = question.getString("r6")
                            a1Points = question.getInt("weight-r1")
                            a2Points = question.getInt("weight-r2")
                            a3Points = question.getInt("weight-r3")
                            a4Points = question.getInt("weight-r4")
                            a5Points = question.getInt("weight-r5")
                            a6Points = question.getInt("weight-r6")
                            if (question.getString("image") != "null") {

                                val image = question.getString("image")
                                val resourceId = resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        a6Points,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5, a6,
                                        null,
                                        j + 1,
                                        resourceId,
                                        null
                                )
                                q1.add(questionDetails)
                            } else {
                                val questionDetails = Question(
                                        text,
                                        type,
                                        a1Points,
                                        a2Points,
                                        a3Points,
                                        a4Points,
                                        a5Points,
                                        a6Points,
                                        0,
                                        a1,
                                        a2,
                                        a3,
                                        a4,
                                        a5, a6,
                                        null,
                                        j + 1,
                                        0,
                                        null
                                )
                                q1.add(questionDetails)
                            }

                        }
                        "free" -> {
                            val a = question.getString("r")
                            val aPoints = question.getInt("weight-r")
                            if (question.getString("image") != "null") {
                                val image = question.getString("image")
                                val resourceId = resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                        text,
                                        type,
                                        aPoints,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        a,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        j + 1,
                                        resourceId,
                                        null)
                                q1.add(questionDetails)
                                q1.add(questionDetails)
                            } else {
                                val questionDetails = Question(
                                        text,
                                        type,
                                        aPoints,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        a,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        j + 1,
                                        0,
                                        null)
                                q1.add(questionDetails)
                            }
                        }
                    }

                }
                break
            }
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }

    private fun getJSON(): String? {
        var json: String? = null
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

    @SuppressLint("NewApi")
    override fun onStart() {
        super.onStart()
        println("size: ${q1.size}")
        q1.clear()
        initialize()


        when (q1[t[itemSelected].currentProgress].type) {
            "onechoice" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.VISIBLE
                answer_7.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                }
            }
            "onechoice-4" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.INVISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
            }
            "onechoice-5" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_5.text = q1[t[itemSelected].currentProgress].a5
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
            }
            "onechoice-7" -> {
                answer_1.visibility = View.VISIBLE
                answer_2.visibility = View.VISIBLE
                answer_3.visibility = View.VISIBLE
                answer_4.visibility = View.VISIBLE
                answer_5.visibility = View.VISIBLE
                answer_6.visibility = View.VISIBLE
                answer_7.visibility = View.VISIBLE
                answer_1.text = q1[t[itemSelected].currentProgress].a1
                answer_2.text = q1[t[itemSelected].currentProgress].a2
                answer_3.text = q1[t[itemSelected].currentProgress].a3
                answer_4.text = q1[t[itemSelected].currentProgress].a4
                answer_5.text = q1[t[itemSelected].currentProgress].a5
                answer_6.text = q1[t[itemSelected].currentProgress].a6
                answer_7.text = q1[t[itemSelected].currentProgress].a7
                answer_1.setOnClickListener {
                    answer_1.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                }
                answer_7.setOnClickListener {
                    answer_7.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser = answer_7.text.toString()
                }
            }
            "free" -> {
                type_answer.visibility = View.VISIBLE
                answer.visibility = View.VISIBLE
                answer_1.visibility = View.INVISIBLE
                answer_2.visibility = View.INVISIBLE
                answer_3.visibility = View.INVISIBLE
                answer_4.visibility = View.INVISIBLE
                answer_5.visibility = View.INVISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE

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
                if (t[itemSelected].currentProgress == t[itemSelected].amountQuestions - 1) {
                    if (q1[t[itemSelected].currentProgress].type != "free") {
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
                        if (q1[t[itemSelected].currentProgress].answerUser!!.equals(q1[t[itemSelected].currentProgress].a1, ignoreCase = true)) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        } else t[itemSelected].points += 0
                    }
                } else {
                    if (q1[t[itemSelected].currentProgress].type != "free") {
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
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a7) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a7_weight
                        }
                    } else {
                        q1[t[itemSelected].currentProgress].answerUser = answer.editText?.text.toString().toLowerCase(Locale.getDefault())
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a1?.toLowerCase(Locale.getDefault()))
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        else t[itemSelected].points += 0
                    }
                    q1[t[itemSelected].currentProgress].number = q1[t[itemSelected].currentProgress].number?.plus(1)
                    t[itemSelected].currentProgress++

                    "${q1[t[itemSelected].currentProgress].number?.minus(1)}/${t[itemSelected].amountQuestions}".also { progress_questions.text = it }
                    println(t[itemSelected].currentProgress)
                    when (q1[t[itemSelected].currentProgress].type) {
                        "onechoice-4" -> {
                            println(t[itemSelected].currentProgress)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.INVISIBLE
                            answer_6.visibility = View.INVISIBLE
                            answer_7.visibility = View.INVISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4

                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                        }
                        "onechoice" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.VISIBLE
                            answer_7.visibility = View.INVISIBLE
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
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }
                            answer_6.setOnClickListener {
                                answer_6.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                            }
                        }
                        "onechoice-5" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.INVISIBLE
                            answer_7.visibility = View.INVISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_5.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4
                            answer_5.text = q1[t[itemSelected].currentProgress].a5
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }

                        }
                        "onechoice-7" -> {
                            println(t[itemSelected].currentProgress)
                            question_text.text = q1[t[itemSelected].currentProgress].question
                            question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                            answer.visibility = View.INVISIBLE
                            type_answer.visibility = View.INVISIBLE
                            answer_1.visibility = View.VISIBLE
                            answer_2.visibility = View.VISIBLE
                            answer_3.visibility = View.VISIBLE
                            answer_4.visibility = View.VISIBLE
                            answer_5.visibility = View.VISIBLE
                            answer_6.visibility = View.VISIBLE
                            answer_7.visibility = View.VISIBLE
                            answer_1.setTextColor(Color.WHITE)
                            answer_2.setTextColor(Color.WHITE)
                            answer_3.setTextColor(Color.WHITE)
                            answer_4.setTextColor(Color.WHITE)
                            answer_5.setTextColor(Color.WHITE)
                            answer_6.setTextColor(Color.WHITE)
                            answer_7.setTextColor(Color.WHITE)
                            answer_1.text = q1[t[itemSelected].currentProgress].a1
                            answer_2.text = q1[t[itemSelected].currentProgress].a2
                            answer_3.text = q1[t[itemSelected].currentProgress].a3
                            answer_4.text = q1[t[itemSelected].currentProgress].a4
                            answer_5.text = q1[t[itemSelected].currentProgress].a5
                            answer_6.text = q1[t[itemSelected].currentProgress].a6
                            answer_7.text = q1[t[itemSelected].currentProgress].a7
                            answer_1.setOnClickListener {
                                answer_1.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_1.text.toString()
                            }
                            answer_2.setOnClickListener {
                                answer_2.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_2.text.toString()
                            }
                            answer_3.setOnClickListener {
                                answer_3.setTextColor(Color.parseColor("#E9980B"))
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                answer_4.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_3.text.toString()
                            }
                            answer_4.setOnClickListener {
                                answer_4.setTextColor(Color.parseColor("#E9980B"))
                                answer_2.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_4.text.toString()
                            }
                            answer_5.setOnClickListener {
                                answer_5.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_5.text.toString()
                            }
                            answer_6.setOnClickListener {
                                answer_6.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_7.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_6.text.toString()
                            }
                            answer_7.setOnClickListener {
                                answer_7.setTextColor(Color.parseColor("#E9980B"))
                                answer_4.setTextColor(Color.WHITE)
                                answer_5.setTextColor(Color.WHITE)
                                answer_6.setTextColor(Color.WHITE)
                                answer_2.setTextColor(Color.WHITE)
                                answer_3.setTextColor(Color.WHITE)
                                answer_1.setTextColor(Color.WHITE)
                                q1[t[itemSelected].currentProgress].answerUser = answer_7.text.toString()
                            }
                        }
                    }
                }
            }
        }
    }
}