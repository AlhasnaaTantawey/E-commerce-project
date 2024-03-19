package com.example.e_commerceproject.data.reposatory.user

import kotlinx.coroutines.flow.Flow

interface UserReposatryInterface {


   suspend fun  isUserLoggedIn() : Flow<Boolean>
   suspend fun userId():Flow<String?>
    suspend fun saveloginState(isLoggedIn :Boolean )

   suspend fun saveUserId(userId :String)


}