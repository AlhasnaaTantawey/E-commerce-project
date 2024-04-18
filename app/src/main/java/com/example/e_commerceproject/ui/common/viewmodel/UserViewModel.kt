package com.example.e_commerceproject.ui.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserPrefernceReposatory
import kotlinx.coroutines.launch

class UserViewModel(
    private val userprefernceRepo: UserPrefernceReposatory
) : ViewModel() {

    suspend fun IsUserLoggedIn() = userprefernceRepo.isUserLoggedIn()
    fun setIsLoggedIn(b: Boolean) {
        viewModelScope.launch {
            userprefernceRepo.saveloginState(b)
        }

    }

    class UserViewModelFactory(
        private val userprefernceRepo
        : UserPrefernceReposatory
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return UserViewModel(userprefernceRepo) as T
            }

            throw IllegalArgumentException("UNKNOEN VIEWMODEL CLASS")
        }
    }


}