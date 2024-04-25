package com.example.e_commerceproject.ui.auth.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_commerceproject.data.datasource.datastore.UserPreferncesDataSource
import com.example.e_commerceproject.data.models.Resource
import com.example.e_commerceproject.data.reposatory.auth.FirebaseAuthReposatoryImpl
import com.example.e_commerceproject.data.reposatory.user.UserPreferenceReposatoryImpl
import com.example.e_commerceproject.databinding.FragmentLoginBinding
import com.example.e_commerceproject.ui.auth.viewmodels.LoginViewModel
import com.example.e_commerceproject.ui.common.views.ProgressDialog
import kotlinx.coroutines.launch
import kotlin.math.log


class LoginFragment : Fragment() {

    val progressDialog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }
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
            loginViewModel.loginState.collect { state->
                Log.d(TAG, "initialViewModel: $state")
               state?.let { resource ->
                   when(resource){

                       is Resource.Error -> {
                           Log.d(TAG, "Resource.Error: ${resource.exception?.message}")
                           progressDialog.dismiss()
                           Toast.makeText(requireActivity(),
                               resource.exception?.message,
                               Toast.LENGTH_LONG).show()
                       }

                       is Resource.Loading -> {
                           progressDialog.show()
                       }

                       is Resource.Success -> {
                           Log.d(TAG, "Resource.Sucess: ${resource.data.toString()}")
                           progressDialog.dismiss()
                           Toast.makeText(requireActivity(),
                               resource.data.toString(),
                               Toast.LENGTH_LONG)
                               .show()
                       }
                       else -> {}
                   }
               }
            }
        }
    }

    private fun initialListener() {
        binding.fragmentLoginButtonSignIn.setOnClickListener {
         loginViewModel.login()
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