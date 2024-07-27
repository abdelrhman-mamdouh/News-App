package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Discover the world's news",
        description = "Stay updated with the latest news stories from around the world.",
        image = R.drawable.boarding1,
    ),
    Page(
        title = "Get personalized recommendations",
        description = "NewsApp will help you find the news you need based on your preferences.",
        image = R.drawable.boarding2,
    ),
    Page(
        title = "Access news articles and videos",
        description = "NewsApp will provide you with access to articles and videos.",
        image = R.drawable.boarding1
    ))