package com.example.e_commerceproject.data.reposatory.auth

import com.example.e_commerceproject.data.models.Resource
import com.google.common.io.Resources
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthReposatory {

    suspend fun loginWithEmailAndPassword(
        email:String ,
        password :String)
    :Flow<Resource<String>>
}