package com.example.e_commerceproject.ui.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserReposatryInterface
import com.example.e_commerceproject.ui.common.viewmodel.UserViewModel

class LoginViewModel(   val userPref :UserReposatryInterface) :ViewModel(){

   //  lateinit var userPreferenceReposatory:UserPreferenceReposatoryImpl



     class  LoginViewModelFactory(private val  userprefernceRepo
                                 :UserPreferenceReposatoryImpl): ViewModelProvider.Factory{

          override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
               if(modelClass.isAssignableFrom(UserViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST") return UserViewModel(userprefernceRepo) as T
               }

               throw IllegalArgumentException("UNKNOEN VIEWMODEL CLASS")
          }
     }



}