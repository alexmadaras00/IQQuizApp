package com.example.iqquizapp

import android.app.Application
import com.example.iqquizapp.DataClasses.Question
import com.example.iqquizapp.DataClasses.Test

class Global:Application() {
    companion object{
        var logged:Boolean=false
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

    }
}