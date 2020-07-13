package com.example.retrofitgithubapi.retrofit

import com.example.retrofitgithubapi.model.GitHubRepo
import com.example.retrofitgithubapi.response.RepoResponseItem
import com.example.retrofitgithubapi.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@JvmSuppressWildcards
interface APIs {

    @GET("/users/{user}")
    suspend fun getUser(
            @Path("user") usernameOrEmail: String
    ): Response<UserResponse>

    @GET("/users/{user}/repos")
    suspend fun getRepo(
            @Path("user") userName: String
    ): Response<ArrayList<RepoResponseItem>>


}