package com.example.e_commerceproject.data.reposatory.auth

import com.example.e_commerceproject.data.models.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthReposatoryImpl(val auth :FirebaseAuth =FirebaseAuth.getInstance()) :FirebaseAuthReposatory{
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<String>> = flow {
        try {
          emit(Resource.Loading())
         val authResult= auth.signInWithEmailAndPassword(email,password).await()
            authResult.user?.let { user->
                emit(Resource.Success(user.uid))

            } ?: run {
                emit(Resource.Error(java.lang.Exception("user not found")))
            }
        }
       catch (e:Exception){
           emit(Resource.Error(e))

       }
    }
}