package com.example.e_commerceproject.ui.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceproject.R
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.ui.login.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

val loginViewModel:LoginViewModel by lazy {
    LoginViewModel(UserPreferenceReposatoryImpl(requireActivity()))
}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
private const val TAG="LoginFragment"
                }


}