package com.arifwidayana.challengechapter7.base.arch

import androidx.navigation.NavDirections

interface BaseContract {
    fun showContent(isVisible: Boolean)
    fun showLoading(isVisible: Boolean)
    fun showMessageToast(isEnabled: Boolean, message: String? = null)
    fun showMessageSnackBar(isEnabled: Boolean, message: String? = null)
    fun moveNav(navUp: Int)
    fun moveNav(direction: NavDirections)
}