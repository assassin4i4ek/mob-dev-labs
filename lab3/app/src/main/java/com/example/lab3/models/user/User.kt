package com.example.lab3.models.user

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val description: String,
//    val imageUrl: String,
    // TODO implement url handling instead
    val image: Int,
    val locationCountry: String,
    val locationCity: String,
    val numPosts: Int,
    val numFollowers: Int,
    val numFollowing: Int,
)