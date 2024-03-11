package com.example.e_commerceproject.data.reposatory.user

interface UserReposatryInterface {
    fun isLoggedIn() :Boolean
    fun saveloginState(isLoggedIn :Boolean )

    fun saveUserId(userId :String)
}