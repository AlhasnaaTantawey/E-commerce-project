package com.example.e_commerceproject.ui.auth.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel :ViewModel() {
    val useId = MutableStateFlow("")
}