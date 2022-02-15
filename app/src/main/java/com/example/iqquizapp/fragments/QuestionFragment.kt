package com.example.iqquizapp.fragments

import Room
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iqquizapp.Adapters.AdapterList
import com.example.iqquizapp.Global
import com.example.iqquizapp.Global.Companion.currentPointsTest1
import com.example.iqquizapp.Global.Companion.currentPointsTest2
import com.example.iqquizapp.Global.Companion.currentPointsTest3
import com.example.iqquizapp.Global.Companion.isGoOffline
import com.example.iqquizapp.Global.Companion.itemSelected
import com.example.iqquizapp.Global.Companion.progressTest1
import com.example.iqquizapp.Global.Companion.progressTest2
import com.example.iqquizapp.Global.Companion.progressTest3
import com.example.iqquizapp.Global.Companion.q1
import com.example.iqquizapp.Global.Companion.t
import com.example.iqquizapp.Global.Companion.test1Done
import com.example.iqquizapp.Global.Companion.test2Done
import com.example.iqquizapp.Global.Companion.test3Done
import com.example.iqquizapp.R
import com.example.iqquizapp.models.Question
import com.example.iqquizapp.repository.database.User
import com.example.iqquizapp.repository.retrofit.INodeJS
import com.example.iqquizapp.repository.retrofit.RetrofitClient
import com.example.iqquizapp.ui.login.LoginResponse
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_question.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.util.*


class QuestionFragment : Fragment(R.layout.fragment_question) {

    var user: User? = null
    private val compositeDisposable = CompositeDisposable()
    private lateinit var myAPI: INodeJS

    @SuppressLint("NewApi")
    override fun onStart() {
        super.onStart()
      
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        val retrofit: Retrofit = RetrofitClient().getInstance()
        myAPI = retrofit.create(INodeJS::class.java)
        if (isLoggedIn)
            user = Room.getInstance(this.requireContext()).user
        println("size: ${q1.size}")
        q1.clear()
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isLoggedIn) {
                    q1.clear()
                    updateData()
                    AdapterList(t).notifyDataSetChanged()
                    AdapterList(t).addNewStatutes(t)

                }
                findNavController().navigate(R.id.nav_list)
            }
        })



        if (isGoOffline)
            when (itemSelected) {
                0 -> {
                    t[itemSelected].currentProgress = progressTest1
                    t[itemSelected].points = currentPointsTest1
                }
                1 -> {
                    t[itemSelected].currentProgress = progressTest2
                    t[itemSelected].points = currentPointsTest2
                }
                2 -> {
                    t[itemSelected].currentProgress = progressTest3
                    t[itemSelected].points = currentPointsTest3
                }
            }
        else {
            when (itemSelected) {
                0 -> {
                    t[itemSelected].currentProgress = user?.test1_progress!!
                    t[itemSelected].points = user?.test1!!
                    t[itemSelected].done = user?.test1_done!!
                    updateTest1SQL(
                        user?.id!!,
                        user?.test1_progress!!,
                        t[itemSelected].points,
                        t[itemSelected].done
                    )
                }
                1 -> {
                    t[itemSelected].currentProgress = user?.test2_progress!!
                    t[itemSelected].points = user?.test2!!
                    t[itemSelected].done = user?.test2_done!!
                    updateTest2SQL(
                        user?.id!!,
                        user?.test2_progress!!,
                        t[itemSelected].points,
                        t[itemSelected].done
                    )
                }
                2 -> {
                    t[itemSelected].currentProgress = user?.test3_progress!!
                    t[itemSelected].points = user?.test3!!
                    t[itemSelected].done = user?.test3_done!!
                    updateTest3SQL(
                        user?.id!!,
                        user?.test3_progress!!,
                        t[itemSelected].points,
                        t[itemSelected].done
                    )
                }

            }

        }
        if (t[itemSelected].done)
            findNavController().navigate(R.id.nav_results)
        configQuestion()

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun configQuestion() {
        initJSON()
        println("Size q: ${q1.size}")
        println("Size t: ${t.size}")
        val isFree = q1[t[itemSelected].currentProgress].type == "free"
        loadQuestion()
        continue_button2.setOnClickListener {
            if (q1[t[itemSelected].currentProgress].answerUser == null || q1[t[itemSelected].currentProgress].answerUser == "")
                if (!isFree)
                    Toast.makeText(
                        this.activity,
                        "Please choose one of the answers.",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    Toast.makeText(
                        this.activity,
                        "Please fill in the answer field.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            else {
                if (t[itemSelected].currentProgress == t[itemSelected].amountQuestions - 1 || t[itemSelected].currentProgress == t[itemSelected].amountQuestions) {
                    if (!isFree) {
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

                    } else {
                        if (q1[t[itemSelected].currentProgress].answerUser!! == q1[t[itemSelected].currentProgress].a1
                        ) {
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        } else t[itemSelected].points += 0
                        type_answer.clearComposingText()
                        type_answer.text=""

                    }
                    t[itemSelected].currentProgress = t[itemSelected].amountQuestions
                    if (isGoOffline)
                        when (itemSelected) {
                            0 -> {
                                currentPointsTest1 = t[itemSelected].points
                                test1Done = true
                            }
                            1 -> {
                                currentPointsTest2 = t[itemSelected].points
                                test2Done = true
                            }

                            2 -> {
                                currentPointsTest3 = t[itemSelected].points
                                test3Done = true
                            }
                        }
                    else {
                        when (itemSelected) {
                            0 -> {
                                user?.test1 = t[itemSelected].points
                                user?.test1_done = true
                                updateTest1SQL(
                                    user?.id!!,
                                    user?.test1_progress!!,
                                    t[itemSelected].points,
                                    t[itemSelected].done
                                )

                            }
                            1 -> {
                                user?.test2 = t[itemSelected].points
                                user?.test2_done = true
                                updateTest2SQL(
                                    user?.id!!,
                                    t[itemSelected].points,
                                    user?.test2_progress!!,
                                    t[itemSelected].done
                                )
                            }

                            2 -> {
                                user?.test3 = t[itemSelected].points
                                user?.test3_done = true

                                updateTest3SQL(
                                    user?.id!!,
                                    user?.test3_progress!!,
                                    t[itemSelected].points,
                                    t[itemSelected].done
                                )
                            }
                        }

                    }
                    t[itemSelected].done = true

                    println(t[itemSelected].points)
                    findNavController().navigate(R.id.nav_results)
                } else {
                    if (!isFree) {
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
                        q1[t[itemSelected].currentProgress].answerUser =
                            answer.text.toString().toLowerCase(Locale.getDefault())
                        if (q1[t[itemSelected].currentProgress].answerUser == q1[t[itemSelected].currentProgress].a1?.toLowerCase(
                                Locale.getDefault()
                            )
                        )
                            t[itemSelected].points += q1[t[itemSelected].currentProgress].a1_weight
                        else t[itemSelected].points += 0
                    }
                    q1[t[itemSelected].currentProgress].number =
                        q1[t[itemSelected].currentProgress].number?.plus(1)
                    t[itemSelected].currentProgress++
                    type_answer.clearComposingText()
                    type_answer.text=""

                    if (isGoOffline) {
                        when (itemSelected) {
                            0 -> progressTest1++
                            1 -> progressTest2++
                            2 -> progressTest3++
                        }
                        when (itemSelected) {
                            0 -> {
                                currentPointsTest1 = t[itemSelected].points
                            }
                            1 -> {
                                currentPointsTest2 = t[itemSelected].points
                            }

                            2 -> {
                                currentPointsTest3 = t[itemSelected].points
                            }
                        }
                    } else {
                        when (itemSelected) {
                            0 -> {
                                currentPointsTest1 = t[itemSelected].points
                                user?.test1 = t[itemSelected].points
                                user?.test1_progress = user?.test1_progress!!.plus(1)
                                updateTest1SQL(
                                    user?.id!!,
                                    user?.test1_progress!!,
                                    t[itemSelected].points,
                                    t[itemSelected].done!!
                                )
                            }
                            1 -> {
                                currentPointsTest2 = t[itemSelected].points
                                user?.test2 = t[itemSelected].points
                                user?.test2_progress = user?.test2_progress!!.plus(1)
                                updateTest2SQL(
                                    user?.id!!,
                                    user?.test2_progress!!,
                                    t[itemSelected].points,
                                    t[itemSelected].done!!
                                )
                            }

                            2 -> {
                                currentPointsTest3 = t[itemSelected].points
                                user?.test3 = t[itemSelected].points
                                user?.test3_progress = user?.test3_progress!!.plus(1)
                                updateTest3SQL(
                                    user?.id!!,
                                    user?.test1_progress!!,
                                    t[itemSelected].points,
                                    t[itemSelected].done!!
                                )
                            }
                        }

                    }

                    println(t[itemSelected].currentProgress)
                    type_answer.clearComposingText()
                    type_answer.text=""
                    loadQuestion()
                }
            }
        }
    }

    private fun loadQuestion() {
        type_answer.clearComposingText()
        type_answer.text=""
        "${q1[t[itemSelected].currentProgress].number}/${t[itemSelected].amountQuestions}".also {
            progress_questions.text = it
        }
        when (q1[t[itemSelected].currentProgress].type) {
            "onechoice-4" -> {
                type_answer.visibility = View.INVISIBLE
                answer.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_1.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_4.text.toString()
                }
            }
            "onechoice" -> {
                type_answer.visibility = View.INVISIBLE
                answer.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_6.text.toString()
                }
            }
            "onechoice-5" -> {
                type_answer.visibility = View.INVISIBLE
                answer.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_5.text.toString()
                }

            }
            "onechoice-7" -> {
                type_answer.visibility = View.INVISIBLE
                answer.visibility = View.INVISIBLE
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
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_1.text.toString()
                }
                answer_2.setOnClickListener {
                    answer_2.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_2.text.toString()
                }
                answer_3.setOnClickListener {
                    answer_3.setTextColor(Color.parseColor("#E9980B"))
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    answer_4.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_3.text.toString()
                }
                answer_4.setOnClickListener {
                    answer_4.setTextColor(Color.parseColor("#E9980B"))
                    answer_2.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_4.text.toString()
                }
                answer_5.setOnClickListener {
                    answer_5.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_5.text.toString()
                }
                answer_6.setOnClickListener {
                    answer_6.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_7.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_6.text.toString()
                }
                answer_7.setOnClickListener {
                    answer_7.setTextColor(Color.parseColor("#E9980B"))
                    answer_4.setTextColor(Color.WHITE)
                    answer_5.setTextColor(Color.WHITE)
                    answer_6.setTextColor(Color.WHITE)
                    answer_2.setTextColor(Color.WHITE)
                    answer_3.setTextColor(Color.WHITE)
                    answer_1.setTextColor(Color.WHITE)
                    q1[t[itemSelected].currentProgress].answerUser =
                        answer_7.text.toString()
                }
            }
            "free" -> {
                type_answer.visibility = View.VISIBLE

                answer.visibility = View.VISIBLE
                println(t[itemSelected].currentProgress)
                answer_1.visibility = View.INVISIBLE
                answer_2.visibility = View.INVISIBLE
                question_text.text = q1[t[itemSelected].currentProgress].question
                question_image.setImageResource(q1[t[itemSelected].currentProgress].image)
                answer_3.visibility = View.INVISIBLE
                answer_4.visibility = View.INVISIBLE
                answer_5.visibility = View.INVISIBLE
                answer_6.visibility = View.INVISIBLE
                answer_7.visibility = View.INVISIBLE
                answer.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        q1[t[itemSelected].currentProgress].answerUser =
                            answer.text.toString().trim()
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        q1[t[itemSelected].currentProgress].answerUser =
                            answer.text.toString().trim()
                    }

                    override fun afterTextChanged(s: Editable?) {
                        q1[t[itemSelected].currentProgress].answerUser =
                            answer.text.toString().trim()
                    }

                })
                q1[t[itemSelected].currentProgress].answerUser =
                    answer.text.toString().trim()
                println("ANSWER: ${q1[t[itemSelected].currentProgress].answerUser} ")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initJSON() = try {
        getJSON()
        val array = JSONObject(getJSON()!!).getJSONArray("tests")
        println("amount of tests: ${array.length()}")
        for (i in 0 until array.length()) {
            val test = array.getJSONObject(i)
            val name = test.getString("name")
            test.getString("description")
            val amountQuestion = test.getInt("amount_question")
            val questions = test.getJSONArray("questions")
            println("current question: $itemSelected")
            if (itemSelected == i) {
                for (j in 0 until questions.length()) {
                    println("number of questions: ${q1.size}")
                    val question = questions.getJSONObject(j)
                    val text = question.getString("text")
                    when (val type = question.getString("type")) {
                        "onechoice-4" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight_r1")
                            val a2Points = question.getInt("weight_r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight_r3")
                            val a4Points = question.getInt("weight_r4")
                            if (question.getString("image") != "null") {
                                question_image.visibility = View.VISIBLE
                                val image = question.getString("image")
                                val resourceId =
                                    resources.getIdentifier(image, "drawable", context?.packageName)
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
                                question_image.visibility = View.INVISIBLE
                                q1.add(questionDetails)
                            }

                        }
                        "onechoice-5" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight_r1")
                            val a2Points = question.getInt("weight_r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight_r3")
                            val a4Points = question.getInt("weight_r4")
                            val a5 = question.getString("r5")
                            val a5Points = question.getInt("weight_r5")
                            if (question.getString("image") != "null") {
                                question_image.visibility = View.VISIBLE
                                val image = question.getString("image")
                                val resourceId =
                                    resources.getIdentifier(image, "drawable", context?.packageName)
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
                                question_image.visibility = View.INVISIBLE
                                q1.add(questionDetails)
                            }
                        }
                        "onechoice-7" -> {
                            val a1 = question.getString("r1")
                            val a2 = question.getString("r2")
                            val a1Points = question.getInt("weight_r1")
                            val a2Points = question.getInt("weight_r2")
                            val a3 = question.getString("r3")
                            val a4 = question.getString("r4")
                            val a3Points = question.getInt("weight_r3")
                            val a4Points = question.getInt("weight_r4")
                            val a5 = question.getString("r5")
                            val a5Points = question.getInt("weight_r5")
                            val a6 = question.getString("r6")
                            val a6Points = question.getInt("weight_r6")
                            val a7 = question.getString("r7")
                            val a7Points = question.getInt("weight_r7")
                            if (question.getString("image") != "null") {
                                question_image.visibility = View.VISIBLE
                                val image = question.getString("image")
                                val resourceId =
                                    resources.getIdentifier(image, "drawable", context?.packageName)
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
                                    a6,
                                    a7,
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
                                    a6,
                                    a7,
                                    j + 1,
                                    0,
                                    null
                                )
                                question_image.visibility = View.INVISIBLE
                                q1.add(questionDetails)
                            }

                        }
                        "onechoice" -> {
                            val question1 = questions.getJSONObject(j)
                            val text1 = question1.getString("text")
                            var a1Points = 0
                            var a2Points = 0
                            var a3Points = 0
                            var a4Points = 0
                            var a5Points = 0
                            var a6Points = 0
                            val a1: String = question1.getString("r1")
                            val a2: String = question1.getString("r2")
                            val a3: String = question1.getString("r3")
                            val a4: String = question1.getString("r4")
                            val a5: String = question1.getString("r5")
                            val a6: String = question1.getString("r6")
                            a1Points = question1.getInt("weight_r1")
                            a2Points = question1.getInt("weight_r2")
                            a3Points = question1.getInt("weight_r3")
                            a4Points = question1.getInt("weight_r4")
                            a5Points = question1.getInt("weight_r5")
                            a6Points = question1.getInt("weight_r6")
                            if (question1.getString("image") != "null") {
                                question_image.visibility = View.VISIBLE
                                val image = question1.getString("image")
                                val resourceId =
                                    resources.getIdentifier(image, "drawable", context?.packageName)
                                val questionDetails = Question(
                                    text1,
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
                                    text1,
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
                                question_image.visibility = View.INVISIBLE
                                q1.add(questionDetails)
                            }

                        }
                        "free" -> {
                            val a = question.getString("r")
                            val aPoints = question.getInt("weight_r")
                            println("Free Answer:$a, $aPoints")
                            if (question.getString("image") != "null") {
                                question_image.visibility = View.VISIBLE
                                val image = question.getString("image")
                                val resourceId =
                                    resources.getIdentifier(image, "drawable", context?.packageName)
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
                                    null
                                )
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
                                    null
                                )

                                q1.add(questionDetails)
                                question_image.visibility = View.INVISIBLE
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

    override fun onDetach() {
        super.onDetach()

    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun updateTest1SQL(id: Int, progress: Int, points: Int, done: Boolean) {
        myAPI.updateTest1(id, progress, points, done).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
                if (response.isSuccessful)
                    Toast.makeText(
                        requireActivity().applicationContext,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                else Toast.makeText(
                    requireActivity().applicationContext,
                    "Failed to update the data",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun updateTest2SQL(id: Int, progress: Int, points: Int, done: Boolean) {
        myAPI.updateTest3(id, progress, points, done).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
                if (response.isSuccessful)
                    Toast.makeText(
                        requireActivity().applicationContext,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                else Toast.makeText(
                    requireActivity().applicationContext,
                    "Failed to update the data",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun updateTest3SQL(id: Int, progress: Int, points: Int, done: Boolean) {
        myAPI.updateTest3(id, progress, points, done).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
                if (response.isSuccessful)
                    Toast.makeText(
                        requireActivity().applicationContext,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                else Toast.makeText(
                    requireActivity().applicationContext,
                    "Failed to update the data",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
    private fun updateData() {
        val isLoggedIn = Room.getInstance(this.requireContext()).isLoggedIn
        if(isLoggedIn)
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
