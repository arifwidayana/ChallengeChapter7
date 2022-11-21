package com.arifwidayana.challengechapter7.presentation.ui.auth.login

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnLogin.setOnClickListener {
                authLoginUser()
            }
            tvRegister.setOnClickListener {
                moveNav(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.loginUserResult.collect {
                if (it is Resource.Success) {
                    showMessageToast(true, it.message)
                    moveNav(R.id.action_loginFragment_to_homeFragment)
                }
                else if (it is Resource.Error) {
                    showMessageSnackBar(true, it.message)
                }
            }
        }
    }

    private fun authLoginUser() {
        binding.apply {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            when {
                checkFormValidation() -> {
                    viewModelInstance.loginUser(
                        LoginRequest(
                            username = username,
                            password = password
                        )
                    )
                }
            }
        }
    }

    private fun checkFormValidation(): Boolean {
        binding.apply {
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
}