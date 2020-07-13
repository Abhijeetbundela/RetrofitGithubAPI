package com.example.retrofitgithubapi.activities.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitgithubapi.R
import com.example.retrofitgithubapi.activities.java.RepositoriesActivity
import com.example.retrofitgithubapi.retrofit.NetworkClient
import com.vipraapp.app.glide.GlideApp
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.user_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class UserActivityKt : AppCompatActivity() {

    private lateinit var newString: String

    private lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        v = findViewById(R.id.include)

        newString = intent.getStringExtra("STRING_I_NEED")!!

        getUser(newString)

        repo_btn.setOnClickListener {

            startActivity(Intent(applicationContext, RepositoriesActivity::class.java).putExtra("username", newString))

        }

    }

    private fun getUser(newString: String) {

        val layoutInflater = LayoutInflater.from(this)
        val myView = layoutInflater.inflate(R.layout.custom_progressbar, null)

        val builder = AlertDialog.Builder(this)

        builder.setView(myView).setCancelable(false)

        val mTextView = myView.findViewById<TextView>(R.id.prg_textview)
        mTextView.text = "Loading...."
        val alertDialog = builder.create()
        alertDialog.show()

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {

                    val service = NetworkClient.create()
                    val response = service.getUser(newString)
                    val data = response.body()!!

                    if (response.isSuccessful) {

                        if (data.name == "") {
                            user.text = "No name provided"
                        } else {
                            user.text = data.name
                        }

                        GlideApp.with(this@UserActivityKt)
                                .load(data.avatar_url)
                                .into(user_image)

                        user.text = data.name
                        followers.text = data.followers.toString()
                        following.text = data.following.toString()
                        login.text = data.login

                        if (data.name == "") {
                            email.text = "No name provided"
                        } else {
                            email.text = data.email
                        }


                    } else {

                        try {
                            val jObjError =
                                    JSONObject(response.errorBody()!!.string())
                            Toast.makeText(
                                    this@UserActivityKt,
                                    jObjError.getString("message"),
                                    Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@UserActivityKt, e.message, Toast.LENGTH_SHORT)
                                    .show()
                        }

                    }

                } catch (e: Exception) {

                    Toast.makeText(
                            this@UserActivityKt,
                            "Something went wrong..",
                            Toast.LENGTH_SHORT
                    ).show()

                } finally {
                    v.visibility = View.VISIBLE
                    repo_btn.visibility = View.VISIBLE
                    alertDialog.dismiss()
                }

            }
        }

    }
}