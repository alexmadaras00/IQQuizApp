package com.example.iqquizapp.DataClasses

data class Test(var name:String, var description:String, var currentProgress:Int, var done: Boolean, var amountQuestions:Int, var questions:ArrayList<Question>, var points:Int)
{

}
