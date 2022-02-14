package com.example.iqquizapp.activities

import Room
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.iqquizapp.Global.Companion.isGoOffline
import com.example.iqquizapp.Global.Companion.logged
import com.example.iqquizapp.R
import com.example.iqquizapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        if (!isGoOffline) {
            ivLogout.visibility = View.VISIBLE
            if (!Room.getInstance(this).isLoggedIn) {
                val i = Intent(applicationContext, LoginActivity::class.java)
                logged = false
                startActivity(i)
            } else {
                val nameUser = Room.getInstance(this).user.username
                name.text = nameUser
                val navController = findNavController(R.id.navHostFragment)
                if (logged) {
                    ivLogout.visibility = View.VISIBLE
                    ivLogin.visibility = View.INVISIBLE
                    ivLogout.setOnClickListener {
                        Room.getInstance(this).clear()
                        logged = false
                        val intent = Intent(this, LoginMenuActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    ivLogin.visibility = View.VISIBLE
                    ivLogout.visibility = View.INVISIBLE
                }
                navController.addOnDestinationChangedListener { _, _, _ ->
                    if (navController.currentDestination?.id == R.id.nav_age) {
                        name.visibility = View.INVISIBLE
                        back_button.visibility = View.VISIBLE
                    } else {
                        name.visibility = View.VISIBLE
                        back_button.visibility = View.INVISIBLE
                    }

                }
            }
        } else {
            val guestText = "Guest"
            name.text = guestText
            ivLogout.visibility = View.INVISIBLE
            ivLogin.visibility = View.VISIBLE
            ivLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            val navController = findNavController(R.id.navHostFragment)
            navController.addOnDestinationChangedListener { _, _, _ ->
                if (navController.currentDestination?.id == R.id.nav_age) {
                    name.visibility = View.INVISIBLE
                    back_button.visibility = View.VISIBLE
                } else {
                    name.visibility = View.VISIBLE
                    back_button.visibility = View.INVISIBLE
                }

            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (!isGoOffline) {
            ivLogout.visibility = View.VISIBLE
            if (!Room.getInstance(this).isLoggedIn) {
                val i = Intent(applicationContext, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                logged = false
                startActivity(i)
            } else {
                val nameUser = Room.getInstance(this).user.username
                name.text = nameUser
                val navController = findNavController(R.id.navHostFragment)
                if (logged) {
                    ivLogout.visibility = View.VISIBLE
                    ivLogout.setOnClickListener {
                        Room.getInstance(this).clear()
                        logged = false
                        val intent = Intent(this, LoginMenuActivity::class.java)
                        startActivity(intent)
                    }
                } else ivLogout.visibility = View.INVISIBLE
                navController.addOnDestinationChangedListener { _, _, _ ->
                    if (navController.currentDestination?.id == R.id.nav_age) {
                        name.visibility = View.INVISIBLE
                        back_button.visibility = View.VISIBLE
                    } else {
                        name.visibility = View.VISIBLE
                        back_button.visibility = View.INVISIBLE
                    }

                }
            }
        } else {
            val guestText = "Guest"
            name.text = guestText
            ivLogout.visibility = View.INVISIBLE
            val navController = findNavController(R.id.navHostFragment)
            navController.addOnDestinationChangedListener { _, _, _ ->
                if (navController.currentDestination?.id == R.id.nav_age) {
                    name.visibility = View.INVISIBLE
                    back_button.visibility = View.VISIBLE
                } else {
                    name.visibility = View.VISIBLE
                    back_button.visibility = View.INVISIBLE
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isGoOffline) {
            ivLogout.visibility = View.VISIBLE
            if (!Room.getInstance(this).isLoggedIn) {
                val i = Intent(applicationContext, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                logged = false
                startActivity(i)
            } else {
                val nameUser = Room.getInstance(this).user.username
                name.text = nameUser
                val navController = findNavController(R.id.navHostFragment)
                if (logged) {
                    ivLogout.visibility = View.VISIBLE
                    ivLogout.setOnClickListener {
                        Room.getInstance(this).clear()
                        logged = false
                        val intent = Intent(this, LoginMenuActivity::class.java)
                        startActivity(intent)
                    }
                } else ivLogout.visibility = View.INVISIBLE
                navController.addOnDestinationChangedListener { _, _, _ ->
                    if (navController.currentDestination?.id == R.id.nav_age) {
                        name.visibility = View.INVISIBLE
                        back_button.visibility = View.VISIBLE
                    } else {
                        name.visibility = View.VISIBLE
                        back_button.visibility = View.INVISIBLE
                    }

                }
            }
        } else {
            val guestText = "Guest"
            name.text = guestText
            ivLogout.visibility = View.INVISIBLE
            val navController = findNavController(R.id.navHostFragment)
            navController.addOnDestinationChangedListener { _, _, _ ->
                if (navController.currentDestination?.id == R.id.nav_age) {
                    name.visibility = View.INVISIBLE
                    back_button.visibility = View.VISIBLE
                } else {
                    name.visibility = View.VISIBLE
                    back_button.visibility = View.INVISIBLE
                }

            }
        }
    }
}

