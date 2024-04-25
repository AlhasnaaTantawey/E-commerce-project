package com.example.e_commerceproject.ui.auth.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.e_commerceproject.data.models.Resource
import com.example.e_commerceproject.data.reposatory.auth.FirebaseAuthReposatory
import com.example.e_commerceproject.data.reposatory.user.UserPrefernceReposatory
import com.example.e_commerceproject.utils.isValidEmail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class LoginViewModel(
    private val userPref :UserPrefernceReposatory,
  private   val authReposatory: FirebaseAuthReposatory
) :ViewModel(){


val loginState :MutableStateFlow<Resource<String>?> =MutableStateFlow(null)
 val email =MutableStateFlow("")
 val password =MutableStateFlow("")

private val isLoginIsValid : Flow<Boolean> = combine(email,password){ email, password ->
    email.isValidEmail()&& password.length >=6

}

     fun login (){
         viewModelScope.launch {
             val emil =email.value
             val password =password.value

             if (isLoginIsValid.first()){
        authReposatory.loginWithEmailAndPassword(emil,password).onEach {resource->
            Log.d(TAG, "Emitted resource:$resource ")
            when(resource){

                is Resource.Error ->loginState.value=
                    Resource.Error(resource.exception ?: Exception("unknown error"))




                is Resource.Loading -> loginState.update { Resource.Loading() }
                is Resource.Success ->{
                   loginState.update { Resource.Success(resource?.data?:"empty user id") }
                }
            }
        }.launchIn(viewModelScope)
             }
             else {
                 loginState.update { Resource.Error(java.lang.Exception("invalid email or password")) }
             }
         }

     }

     class  LoginViewModelFactory(private val  userprefernceRepo:UserPrefernceReposatory,
                               private   val authReposatory: FirebaseAuthReposatory
     ): ViewModelProvider.Factory{

          override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
               if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST") return LoginViewModel(userprefernceRepo,authReposatory) as T
               }

               throw IllegalArgumentException("UNKNOEN VIEWMODEL CLASS")
          }
     }

companion object{
    private  const val TAG ="LoginViewModel"
}

}