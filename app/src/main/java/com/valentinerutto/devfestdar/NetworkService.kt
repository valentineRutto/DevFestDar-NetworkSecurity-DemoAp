package com.valentinerutto.devfestdar

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkService {
    @GET("users/{user}/followers")
    fun loadFollowers(
        @Path("user") user: String
    ): Call<List<DataClass>>

    @GET("users/{username}/repos")
    fun UserRepositories(@Path("username") userName: String): Call<List<RepoList>>

    companion object {

        var BASE_URL = "https://api.github.com/"

        fun create(): NetworkService {
            val logging = HttpLoggingInterceptor()
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }


            val client = OkHttpClient.Builder()
                .certificatePinner(SslPinningBuilder.getCertificatePinnerBuilder())
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(NetworkService::class.java)

        }
    }
}