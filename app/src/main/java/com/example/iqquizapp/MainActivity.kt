package com.example.iqquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.iqquizapp.Global.Companion.logged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameUser=intent.getStringExtra("username")
        name.text=nameUser
        val navController=findNavController(R.id.navHostFragment)
        if(logged) {
            logout.visibility=View.VISIBLE
            logout.setOnClickListener {
                logged=false
                val intent = Intent(this, LoginMenuActivity::class.java)
                startActivity(intent)
            }
        }
        else logout.visibility=View.INVISIBLE
        navController.addOnDestinationChangedListener { _, _, _ ->
            if (navController.currentDestination?.id == R.id.nav_age ) {
                name.visibility = View.INVISIBLE
                back_button.visibility = View.VISIBLE
            } else {
                name.visibility = View.VISIBLE
                back_button.visibility = View.INVISIBLE
            }

        }

    }
}