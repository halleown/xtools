package com.halleown.xtools.network.model

data class User(
    val userId: Int,
    val username: String,
    val email: String,
    val avatar: String,
    val birthdate: String,
    val registeredAt: String
)