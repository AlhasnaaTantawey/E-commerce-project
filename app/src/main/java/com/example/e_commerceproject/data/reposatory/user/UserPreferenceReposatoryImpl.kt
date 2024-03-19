package com.example.e_commerceproject.data.reposatory.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.USER_ID
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceReposatoryImpl(private val userPreferncesDataSource:
                                   UserPreferncesDataSource) : UserReposatryInterface {
    override suspend fun isUserLoggedIn(): Flow<Boolean> {
      return  userPreferncesDataSource.isUserLoggedIn
    }

    override suspend fun userId(): Flow<String?> {
      return  userPreferncesDataSource.userId
    }


    override suspend fun saveloginState(isLoggedIn: Boolean) {
        userPreferncesDataSource.saveloginState(isLoggedIn)
    }

    override suspend fun saveUserId(userId: String) {
        userPreferncesDataSource.saveUserId(userId)
    }


}