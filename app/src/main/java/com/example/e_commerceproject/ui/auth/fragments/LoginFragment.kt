package com.example.e_commerceproject.ui.auth.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.reposatory.auth.FirebaseAuthReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.databinding.FragmentLoginBinding
import com.example.e_commerceproject.ui.auth.viewmodels.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModel.LoginViewModelFactory(
            UserPreferenceReposatoryImpl(UserPreferncesDataSource(requireActivity())),
            FirebaseAuthReposatoryImpl()
        )
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           initialListener()
           initialViewModel()
    }

    private fun initialViewModel() {
        lifecycleScope.launch {
            loginViewModel.email.collect {
                Log.d(TAG, "initialViewModel:email changed = $it")
            }
        }
    }

    private fun initialListener() {
        binding.fragmentLoginButtonSignIn.setOnClickListener {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "LoginFragment"
    }


}