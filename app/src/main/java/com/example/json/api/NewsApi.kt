package com.example.json.api

import com.example.json.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String = "us",
        @Query("page")
        pageNumber:Int =1,
        @Query("apiKey")
        apiKey:String = "9916ae955df94fbf885d264a385b027f"
    ): Response<News>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int =1,
        @Query("apiKey")
        apiKey:String = "9916ae955df94fbf885d264a385b027f"
    ):Response<News>
}