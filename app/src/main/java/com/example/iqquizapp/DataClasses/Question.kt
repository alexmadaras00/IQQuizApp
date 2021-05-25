package com.example.iqquizapp.DataClasses

data class Question(
    var question:String,
    var type:String,
    var a1_weight:Int,
    var a2_weight:Int, var a3_weight:Int,
    var a4_weight:Int,
    var a5_weight:Int,
    var a6_weight:Int
    ,
    var a1:String,
    var a2:String,
    var a3: String?,
    var a4: String?,
    var a5: String?,
    var a6: String?,
    var number: Int?,
    var image:Int,
    var answerUser: String?
)
{



}
