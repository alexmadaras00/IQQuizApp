package com.example.iqquizapp

import android.app.Application
import com.example.iqquizapp.models.Question
import com.example.iqquizapp.models.Test

class Global:Application() {
    companion object{
        var added: Boolean = false
        var logged: Boolean = false
        val t=ArrayList<Test>()
        var itemSelected:Int = 0
        var tests=ArrayList<Test>()
        var q1=ArrayList<Question>()
        var q2=ArrayList<Question>()
        var q3=ArrayList<Question>()
        var q4=ArrayList<Question>()
        var a=ArrayList<String>()
        var p1:Int=0
        var ageSelected:String="1-16"
        var progressTest1:Int = 0
        var progressTest2:Int = 0
        var progressTest3:Int = 0
        var currentPointsTest1: Int = 0
        var currentPointsTest2: Int = 0
        var currentPointsTest3: Int = 0
        var test1Done :Boolean = false
        var test2Done :Boolean = false
        var test3Done :Boolean = false
        var isGoOffline = false

    }
}