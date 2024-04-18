package com.example.e_commerceproject.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.ECOMMERCE_PREFERENCES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferncesDataSource(private val context:Context) {


    val isUserLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefernces ->
            prefernces[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
    }


    val userId:Flow<String?> = context.dataStore.data.map { prefernces ->
        prefernces[DataStoreKeys.USER_ID]
    }

     suspend fun saveloginState(isLoggedIn: Boolean) {
         context.dataStore.edit{ prefernces ->
            prefernces[DataStoreKeys.IS_USER_LOGGED_IN]=isLoggedIn
        }
    }

     suspend fun saveUserId(userId: String) {
         context.dataStore.edit{ prefernces ->
            prefernces[DataStoreKeys.USER_ID]=userId
        }
    }




}