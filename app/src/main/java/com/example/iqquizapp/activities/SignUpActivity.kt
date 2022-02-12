package com.example.iqquizapp.activities

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iqquizapp.Global.Companion.logged
import com.example.iqquizapp.R
import com.example.iqquizapp.repository.retrofit.INodeJS
import com.example.iqquizapp.repository.retrofit.RetrofitClient
import com.example.iqquizapp.ui.login.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Retrofit
import java.math.BigInteger
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException
import java.nio.ByteOrder


class SignUpActivity : AppCompatActivity() {
    private lateinit var myAPI: INodeJS
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val retrofit: Retrofit = RetrofitClient().getInstance()
        myAPI = retrofit.create(INodeJS::class.java)
        already.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener {
            register(
                email.text.toString(),
                mail.text.toString(),
                password.text.toString(),
                phone_number.text.toString()
            )
            val i = Intent(this, MainActivity::class.java)
            logged = true
            i.putExtra("email", email.text.toString())
            startActivity(i)
            finish()
        }
    }

    private fun register(username: String, email: String, password: String, phone: String) {
        compositeDisposable.add(myAPI.registerUser(username, password, email, phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Toast.makeText(this, "Account added", Toast.LENGTH_SHORT).show()
            })
    }
    private fun getLocalWifiIpAddress(): String? {
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        var ipAddress = wifiManager.connectionInfo.ipAddress
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress)
        }
        val ipByteArray: ByteArray = BigInteger.valueOf(ipAddress.toLong()).toByteArray()
        val ipAddressString: String? = try {
            InetAddress.getByAddress(ipByteArray).hostAddress
        } catch (ex: UnknownHostException) {
            null
        }
        return ipAddressString
    }


}