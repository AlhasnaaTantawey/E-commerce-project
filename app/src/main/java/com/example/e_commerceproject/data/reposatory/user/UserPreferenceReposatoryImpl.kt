package com.example.e_commerceproject.data.reposatory.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.USER_ID
import com.example.e_commerceproject.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceReposatoryImpl(var context: Context) : UserReposatryInterface {

    // private lateinit var context: Context
    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { prefernces ->
            prefernces[IS_USER_LOGGED_IN] ?: false
        }
    }


    override suspend fun saveloginState(isLoggedIn: Boolean) {
        context.dataStore.edit{ prefernces ->
            prefernces[IS_USER_LOGGED_IN]=isLoggedIn
        }
    }

    override suspend fun saveUserId(userId: String) {
        context.dataStore.edit{ prefernces ->
            prefernces[USER_ID]=userId
        }
    }
}