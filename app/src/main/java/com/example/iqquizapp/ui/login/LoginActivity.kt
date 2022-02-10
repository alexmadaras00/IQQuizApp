package com.example.iqquizapp.ui.login

import Room
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.iqquizapp.Global
import com.example.iqquizapp.activities.ForgotPasswordActivity
import com.example.iqquizapp.Global.Companion.logged
import com.example.iqquizapp.activities.MainActivity
import com.example.iqquizapp.R
import com.example.iqquizapp.Retrofit.INodeJS
import com.example.iqquizapp.Retrofit.RetrofitClient
import com.example.iqquizapp.activities.SignUpActivity
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {


    private lateinit var myAPI: INodeJS

    private val compositeDisposable = CompositeDisposable()
    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val retrofit: Retrofit = RetrofitClient().getInstance()
        myAPI = retrofit.create(INodeJS::class.java)
        val email = findViewById<TextInputEditText>(R.id.email)
        val password = findViewById<TextInputEditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        //   val loading = findViewById<ProgressBar>(R.id.loading)
        noacc.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        forgot.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {

            val validateInput = LoginValidator(
                this,
                email,
                password
            )
            val emailVerified: Boolean = validateInput.validateEmail()
            val passVerified: Boolean = validateInput.validatePassword()
            println("Mail:" + emailVerified)
            println("Pass:" + passVerified)
            if (emailVerified && passVerified) {
                val mail = email.text.toString().trim()
                val pass = password.text.toString().trim()
                login(mail, pass)
            }


        }
    }

    private fun login(mail: String, pass: String) {

        myAPI.loginUser(mail, pass).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Room.getInstance(applicationContext).saveUser(response.body()?.user!!)
                    if(Room.getInstance(applicationContext).user.username != "null" ) {
                        Global.isGoOffline = false
                        val i = Intent(applicationContext, MainActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        logged = true
                        startActivity(i)
                    }
                    else toast("Invalid credentials. Please try again.")
                }
                else if(response.code()==401) toast("Session expired! Try again!")
                else toast("Wrong e-mail or password!")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }


    private fun handleError(it: Throwable?) {
        Log.d("handlleError", "Error: $it")
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        if(Room.getInstance(this).isLoggedIn){
            val i = Intent(applicationContext, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            logged = true
            startActivity(i)
        }
    }
}

