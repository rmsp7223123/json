package com.example.json

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)