package com.example.retrofitgithubapi.activities.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgithubapi.R
import com.example.retrofitgithubapi.adapter.ReposAdapter
import com.example.retrofitgithubapi.response.RepoResponseItem
import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivityKt : AppCompatActivity() {

    private lateinit var mReceivedUserName: String
    private val myadapter: RecyclerView.Adapter<*>? = null

    private val myDataSource: ArrayList<RepoResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)


    }
}