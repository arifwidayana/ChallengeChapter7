package com.arifwidayana.challengechapter7.base.arch

interface BaseContract {
    interface BaseView {
        fun showContent(isVisible: Boolean)
        fun showLoading(isVisible: Boolean)
        fun showMessageToast(isEnabled: Boolean, message: String? = null)
        fun showMessageSnackBar(isEnabled: Boolean, message: String? = null)
    }
}