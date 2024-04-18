package com.example.e_commerceproject.data.reposatory.user

import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import kotlinx.coroutines.flow.Flow

class UserPreferenceReposatoryImpl(private val userPreferncesDataSource:
                                   UserPreferncesDataSource) : UserPrefernceReposatory {
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