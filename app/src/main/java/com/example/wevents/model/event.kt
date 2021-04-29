package com.example.wevents

data class event(
    val imgSrc: String,
    val name: String,
    val org: String,
    val description : String
)

data class eventDB(
    val events : ArrayList<event>
)