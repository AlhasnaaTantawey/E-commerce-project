package com.example.e_commerceproject.ui.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserReposatryInterface

class LoginViewModel(   val userPref :UserReposatryInterface) :ViewModel(){

     lateinit var userPreferenceReposatory:UserPreferenceReposatoryImpl


}