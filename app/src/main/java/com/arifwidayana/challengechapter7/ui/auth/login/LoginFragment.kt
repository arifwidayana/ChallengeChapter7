package com.arifwidayana.challengechapter7.ui.auth.login

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.preference.SharedHelper
import com.arifwidayana.challengechapter7.databinding.FragmentLoginBinding
import com.arifwidayana.challengechapter7.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
), LoginContract.View {
    private lateinit var shared: SharedHelper

    override fun initView() {
        onClick()
        observeData()
    }

    override fun onClick() {
        shared = SharedHelper(requireContext())

        getViewBinding().apply {
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                authLoginUser()
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().pbLoading.isVisible = isVisible
    }

    override fun observeData() {
        showLoading(false)
        getViewModel().getLoginUserLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), R.string.login_success, Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainHomepageFragment)
                    getViewModel().getLoginUserLiveData().removeObservers(viewLifecycleOwner)
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), R.string.login_failed, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun authLoginUser() {
        getViewBinding().apply {
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            when {
                checkFormValidation() -> {
                    getViewModel().loginUser(user, pass)
                    loginSession(user, pass)
                }
            }

        }
    }

    override fun checkFormValidation(): Boolean {
        getViewBinding().apply {
            var isValid = true
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            when {
                user.isEmpty() && pass.isEmpty() -> {
                    tfUsername.error = "Fill the username"
                    tfPassword.error = "Fill the password"
                    isValid = false
                }
                user.isEmpty() -> {
                    tfUsername.error = "Fill the username"
                    isValid = false
                }
                pass.isEmpty() -> {
                    tfPassword.error = "Fill the password"
                    isValid = false
                }
                else -> {
                    tfUsername.error = null
                    tfPassword.error = null
                }
            }
            return isValid
        }
    }

    override fun onStart() {
        super.onStart()
        if (shared.getBoolean(Constant.LOGIN, false)) {
            findNavController().navigate(R.id.action_loginFragment_to_mainHomepageFragment)
        }
    }

    private fun loginSession(user: String, pass: String) {
        shared.apply {
            put(Constant.USERNAME, user)
            put(Constant.PASSWORD, pass)
            put(Constant.LOGIN, true)
        }
    }

}