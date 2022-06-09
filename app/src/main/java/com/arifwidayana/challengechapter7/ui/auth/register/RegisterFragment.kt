package com.arifwidayana.challengechapter7.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseViewModel
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseViewModel<RegisterViewModel>(
), RegisterContract.View {

    override fun initView() {
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HeaderRegisterPage()
                    RegisterAccount {
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }

                }
            }
        }
    }

    @Composable
    private fun HeaderRegisterPage() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.Black
                )
            )
        }
    }

    @Composable
    private fun RegisterAccount(onClickHaveAccount:() -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val name = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val age = remember { mutableStateOf("") }
            val phone = remember { mutableStateOf("") }
            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text(text = "Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                label = { Text(text = "Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = phone.value,
                onValueChange = { phone.value = it },
                label = { Text(text = "Phone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text(text = "Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Have Account?",
                modifier = Modifier
                    .padding(start = 250.dp)
                    .clickable(onClick = onClickHaveAccount),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    authRegisterUser(
                        name = name.value,
                        email = email.value,
                        age = age.value.toInt(),
                        phone = phone.value,
                        user = username.value,
                        pass = password.value
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Register")
            }

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

    private fun authRegisterUser(name: String, email: String, age: Int, phone: String, user: String, pass: String) {
        getViewModel().registerUser(
            UserEntity(
                id = null,
                name = name,
                profile = null,
                email = email,
                age = age,
                phone_number = phone,
                username = user,
                password = pass
            )
        )
    }

}