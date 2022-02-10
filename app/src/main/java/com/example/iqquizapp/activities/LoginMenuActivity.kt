package com.example.iqquizapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iqquizapp.Global
import com.example.iqquizapp.Global.Companion.isGoOffline
import com.example.iqquizapp.R
import com.example.iqquizapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login_menu.*

class LoginMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_menu)
        login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signup_button.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        offline_button.setOnClickListener {
            isGoOffline = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
    override fun onStart() {
        super.onStart()
        if(Room.getInstance(this).isLoggedIn){
            isGoOffline = false
            val i = Intent(applicationContext, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Global.logged = true
            startActivity(i)
        }
    }
}