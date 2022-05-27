package com.arifwidayana.challengechapter7.ui.homepage.profile.user

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import com.arifwidayana.challengechapter7.data.local.preference.SharedHelper
import com.arifwidayana.challengechapter7.databinding.FragmentProfileUserBinding
import com.arifwidayana.challengechapter7.utils.Constant
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileUserFragment : BaseFragment<FragmentProfileUserBinding, ProfileUserViewModel>(
    FragmentProfileUserBinding::inflate
), ProfileUserContract.View {
    private lateinit var shared: SharedHelper

    override fun initView() {
        shared = SharedHelper(requireContext())
        getData()
        onClick()
    }

    override fun getData() {
        getViewModel().apply {
            getUser(shared.getString(Constant.USERNAME).toString())
        }
    }

    private fun onClick() {
        getViewBinding().apply {
            ivBackHome.setOnClickListener {
                findNavController().navigate(R.id.action_profileUserFragment_to_homeFragment)
            }

            cvProfile.setOnClickListener {
                openImagePicker()
            }

            btnEdit.setOnClickListener {
                findNavController().navigate(R.id.action_profileUserFragment_to_editProfileFragment)
            }

            btnLogout.setOnClickListener {
                shared.clear()
                Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profileUserFragment_to_loginFragment)
            }
        }
    }

    override fun observeData() {
        getViewModel().apply {
            with(getViewBinding()){
                getUserResult().observe(viewLifecycleOwner) {
                    tvGetUsername.text = it.username
                    Glide.with(root)
                        .load(it.profile)
                        .into(ivProfile)
                    tvGetName.text = it.name
                    tvGetEmail.text = it.email
                    tvGetAge.text = it.age.toString()
                    tvGetPhone.text = it.phone_number
                }
            }
        }

    }

    private fun openImagePicker() {
        ImagePicker.with(this)
            .crop()
            .saveDir(File(activity?.externalCacheDir, "Image Picker"))
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data
                    fileUri?.let { saveImage(it) }
                }

                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }

            }
        }

    private fun saveImage(uri: Uri) {
        getViewModel().apply {
            with(getViewBinding()) {
                ivProfile.setImageURI(uri)
                getUserResult().observe(viewLifecycleOwner){
                    val newImageUser = UserEntity(
                        it.id,
                        it.name,
                        uri.toString(),
                        it.email,
                        it.age,
                        it.phone_number,
                        it.username,
                        it.password
                    )
                    updateUser(newImageUser)
                }
            }
        }
    }

}