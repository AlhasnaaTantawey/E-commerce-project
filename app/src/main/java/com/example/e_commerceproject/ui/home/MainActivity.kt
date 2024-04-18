package com.example.e_commerceproject.ui.home

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.e_commerceproject.R
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.ui.common.viewmodel.UserViewModel
import com.example.e_commerceproject.ui.auth.AuthActivity
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels() {
        UserViewModel.UserViewModelFactory(
            UserPreferenceReposatoryImpl(UserPreferncesDataSource(this@MainActivity)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Main) {
            var isLoggedIn = userViewModel.IsUserLoggedIn().first()

            if (isLoggedIn) {//true
                setContentView(R.layout.activity_main)
            }
            else{//false
                goAuthActivity()
                userViewModel.setIsLoggedIn(isLoggedIn.not())
            }

        }

    }

    private fun goAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
            .apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val options = ActivityOptions.makeCustomAnimation(
            this,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        startActivity(intent)

    }


    private fun initSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
            // Add a callback that's called when the splash screen is animating to the
            // app content.
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 1000L

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd { splashScreenView.remove() }

                // Run your animation.
                slideUp.start()
            }
        } else {
            setTheme(R.style.Theme_ECommerceProject)
        }
    }

    companion object {
        private const val Tag = "MainActivity"
    }

}