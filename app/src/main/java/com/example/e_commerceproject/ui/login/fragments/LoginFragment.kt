package com.example.e_commerceproject.ui.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.e_commerceproject.R
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.databinding.FragmentLoginBinding
import com.example.e_commerceproject.ui.login.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

//val loginViewModel:LoginViewModel by lazy {
//    LoginViewModel(UserPreferenceReposatoryImpl(UserPreferncesDataSource(requireActivity())))
//}

    private  val loginViewModel :LoginViewModel by viewModels {
        LoginViewModel.LoginViewModelFactory(
            UserPreferenceReposatoryImpl(UserPreferncesDataSource(requireActivity()))
        )
    }

    private  var _binding :FragmentLoginBinding? =null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object {
private const val TAG="LoginFragment"
                }


}