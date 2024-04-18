package com.example.e_commerceproject.ui.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.e_commerceproject.data.reposatory.auth.FirebaseAuthReposatory
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserPrefernceReposatory
import com.example.e_commerceproject.ui.common.viewmodel.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(
    private val userPref :UserPrefernceReposatory,
  private   val authReposatory: FirebaseAuthReposatory
) :ViewModel(){

 val email =MutableStateFlow("")
 val password =MutableStateFlow("")



     fun login (){
         val emil =email.value
         val password =password.value

         if (emil.isNotEmpty() && password.isNotEmpty()){
             
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



}