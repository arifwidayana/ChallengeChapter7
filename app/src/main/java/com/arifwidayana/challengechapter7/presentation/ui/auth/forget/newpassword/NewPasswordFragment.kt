package com.arifwidayana.challengechapter7.presentation.ui.auth.forget.newpassword

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.data.local.model.request.NewPasswordRequest
import com.arifwidayana.challengechapter7.databinding.FragmentNewPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding, NewPasswordViewModel>(
    FragmentNewPasswordBinding::inflate
) {
    private val args by navArgs<NewPasswordFragmentArgs>()

    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnSubmit.setOnClickListener {
                viewModelInstance.updateUser(
                    NewPasswordRequest(
                        username = args.username.toString(),
                        password = etConfirmPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance
        }
    }
}