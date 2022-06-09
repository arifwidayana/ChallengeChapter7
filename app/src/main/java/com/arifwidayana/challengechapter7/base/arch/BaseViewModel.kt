package com.arifwidayana.challengechapter7.base.arch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseViewModel<VM: ViewModel>(
): Fragment(), BaseContract.BaseView {

    @Inject
    lateinit var viewModelInstance: VM

    fun getViewModel(): VM = viewModelInstance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    abstract fun initView()
    override fun observeData() {
        //do nothing
    }

    override fun showContent(isVisible: Boolean) {
        //do nothing
    }

    override fun showLoading(isVisible: Boolean) {
        //do nothing
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        when{
            isErrorEnabled -> {
                Toast.makeText(requireContext(), "Message: $msg", Toast.LENGTH_SHORT).show()
            }
        }
    }
}