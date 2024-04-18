package com.example.e_commerceproject.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.e_commerceproject.R
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.ui.auth.viewmodels.AuthViewModel
import com.example.e_commerceproject.ui.common.viewmodel.UserViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val userViewModel: UserViewModel by viewModels() {
        UserViewModel.UserViewModelFactory(
            UserPreferenceReposatoryImpl(UserPreferncesDataSource(this@AuthActivity))
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        // Find the NavController associated with the NavHostFragment
        navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
//        lifecycleScope.launch {
//            val myStartDest : Int = if(userViewModel.IsUserLoggedIn().first()) R.id.homeFragment else R.id.loginFragment
//                navController.graph.setStartDestination(
//                    myStartDest
//                )
    //    }
    }
}