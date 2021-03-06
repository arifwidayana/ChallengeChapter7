package com.arifwidayana.challengechapter7.ui.auth.register

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import com.arifwidayana.challengechapter7.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    FragmentRegisterBinding::inflate
), RegisterContract.View {

    override fun initView() {
        onClick()
        observeData()
    }

    private fun onClick() {
        getViewBinding().apply {
            tvSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            btnRegister.setOnClickListener {
                authRegisterUser()
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        getViewBinding().apply {
            var isValid = true

            when {
                etRegisterName.text.toString().isEmpty() -> {
                    tfRegisterName.error = "Fill the name"
                    isValid = false
                }
                etRegisterEmail.text.toString().isEmpty() -> {
                    tfRegisterEmail.error = "Fill the email"
                    isValid = false
                }
                etRegisterAge.text.toString().isEmpty() -> {
                    tfRegisterAge.error = "Fill the age"
                    isValid = false
                }
                etRegisterPhoneNumber.text.toString().isEmpty() -> {
                    tfRegisterPhoneNumber.error = "Fill the phone"
                    isValid = false
                }
                etRegisterUsername.text.toString().isEmpty() -> {
                    tfRegisterUsername.error = "Fill the username"
                    isValid = false
                }
                etRegisterPassword.text.toString().isEmpty() -> {
                    tfRegisterPassword.error = "Fill the password"
                    isValid = false
                }
                else -> {
                    tfRegisterName.error = null
                    tfRegisterEmail.error = null
                    tfRegisterAge.error = null
                    tfRegisterPhoneNumber.error = null
                    tfRegisterUsername.error = null
                    tfRegisterPassword.error = null
                }
            }
            return isValid
        }
    }

    override fun observeData() {
        showLoading(false)
        getViewModel().apply {
            getRegisterUserLiveData().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        getViewModel().getRegisterUserLiveData().removeObservers(viewLifecycleOwner)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "Register Fail, Please make sure the fill",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }

    private fun authRegisterUser() {
        getViewBinding().apply {
            if (checkFormValidation()) {
                getViewModel().registerUser(
                    UserEntity(
                        id = null,
                        name = etRegisterName.text.toString(),
                        profile = null,
                        email = etRegisterEmail.text.toString(),
                        age = etRegisterAge.text.toString().toInt(),
                        phone_number = etRegisterPhoneNumber.text.toString(),
                        username = etRegisterUsername.text.toString(),
                        password = etRegisterPassword.text.toString()
                    )
                )
            }

        }
    }

}