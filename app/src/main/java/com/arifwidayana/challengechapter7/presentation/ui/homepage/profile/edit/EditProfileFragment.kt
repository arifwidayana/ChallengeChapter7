package com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit

import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.databinding.FragmentEditProfileBinding
import com.arifwidayana.challengechapter7.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>(
    FragmentEditProfileBinding::inflate
), EditProfileContract.View {
    private lateinit var shared: SharedHelper

    override fun initView() {
        shared = SharedHelper(requireContext())
        getData()
        onClick()
    }

    override fun getData() {
        getViewModel().apply {
            getUser(shared.getString(Constant.USERNAME_PREF).toString())
        }
    }

    private fun onClick() {
        getViewBinding().apply {
            ivBackProfile.setOnClickListener {
                findNavController().navigate(R.id.action_editProfileFragment_to_profileUserFragment)
            }

            btnSave.setOnClickListener {
                saveDataProfile()
            }

            btnDelete.setOnClickListener {
                deleteDataUser()
            }
        }
    }

    override fun observeData() {
        getViewModel().apply {
            with(getViewBinding()) {
                getUserResult().observe(viewLifecycleOwner) {
                    etEditName.setText(it.name)
                    etEditEmail.setText(it.email)
                    etEditAge.setText(it.age.toString())
                    etEditPhoneNumber.setText(it.phone_number)
                }
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        getViewBinding().apply {
            var isValid = true
            when {
                etEditName.text.toString().isEmpty() -> {
                    tfEditName.error = "Fill the name"
                    isValid = false
                }
                etEditEmail.text.toString().isEmpty() -> {
                    tfEditEmail.error = "Fill the email"
                    isValid = false
                }
                etEditAge.text.toString().isEmpty() -> {
                    tfEditAge.error = "Fill the age"
                    isValid = false
                }
                etEditPhoneNumber.text.toString().isEmpty() -> {
                    tfEditPhoneNumber.error = "Fill the phone"
                    isValid = false
                }
                else -> {
                    tfEditName.error = null
                    tfEditEmail.error = null
                    tfEditAge.error = null
                    tfEditPhoneNumber.error = null
                }
            }
            return isValid
        }
    }

    private fun saveDataProfile() {
        getViewModel().apply {
            with(getViewBinding()) {
                if (checkFormValidation()) {
                    getUserResult().observe(viewLifecycleOwner) {
                        val newDataUser = UserEntity(
                            id = it.id,
                            name = etEditName.text.toString(),
                            profile = it.profile,
                            email = etEditEmail.text.toString(),
                            age = etEditAge.text.toString().toInt(),
                            phone_number = etEditPhoneNumber.text.toString(),
                            username = it.username,
                            password = it.password
                        )
                        updateUser(newDataUser)
                    }
                }
            }
        }
        findNavController().navigate(R.id.action_editProfileFragment_to_profileUserFragment)
    }

    private fun deleteDataUser() {
        shared.clear()

    }

}