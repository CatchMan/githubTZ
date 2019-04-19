package com.catchman.githubtesttask.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.catchman.githubtesttask.util.logi
import com.catchman.githubtesttask.GitHubApp
import com.catchman.githubtesttask.di.component.DaggerFragmentComponent
import com.catchman.githubtesttask.di.component.FragmentComponent
import com.catchman.githubtesttask.di.modul.ActivityModule
import com.catchman.githubtesttask.di.modul.FragmentModule


abstract class BaseFragment : Fragment(), IView {

    protected lateinit var fragmentComponent: FragmentComponent

    var activity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity){
            this.activity = context
            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule())
                    .activityModule(ActivityModule(context))
                    .applicationComponent((context.application as GitHubApp).appComponent)
                    .build()
            context.onFragmentAttached()
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun showProgress(show: Boolean) {
        if (isAdded) activity?.showProgress(show)
    }

    override fun isNetworkConnected(): Boolean = activity?.isNetworkConnected() ?: false

    override fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    protected fun showDialog(title: String, message: String){
        AlertDialog.Builder(context!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, which ->  message.logi()}
                .show()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}