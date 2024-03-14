package com.example.e_commerceproject.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.e_commerceproject.data.datasource.datastore.DataStoreKeys.ECOMMERCE_PREFERENCES


object DataStoreKeys {

const val ECOMMERCE_PREFERENCES ="e_commerce_prefernces"
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
   val USER_ID = stringPreferencesKey("user_id")
  }
//create file ecommerce prefernce
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ECOMMERCE_PREFERENCES)