package com.example.retrofitgithubapi.activities.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitgithubapi.R
import com.example.retrofitgithubapi.activities.java.UserActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {
            val inputUserName: String = input_username.editText!!.text.toString().trim { it <= ' ' }
            if (inputUserName.isEmpty()) {
                input_username.error = "Enter UserName"
            } else {
                input_username.isErrorEnabled = false
                startActivity(Intent(applicationContext, UserActivityKt::class.java).putExtra("STRING_I_NEED", inputUserName))
            }
        }

    }
}