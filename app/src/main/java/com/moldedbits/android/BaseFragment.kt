package com.moldedbits.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import com.moldedbits.android.dialogs.LoadingDialog
import com.moldedbits.android.utils.fragmenttransactionhandler.FragmentTransactionHandler

abstract class BaseFragment : Fragment() {

    lateinit var handler: FragmentTransactionHandler
        protected set

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = FragmentTransactionHandler()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //enabling toolbar to have menu items in fragment
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        handler.setActivity(activity)
        handler.resume()
    }

    override fun onPause() {
        super.onPause()
        handler.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.setActivity(null)
    }

    protected fun cancelLoadingDialog() {
        if (loadingDialog != null
                && loadingDialog!!.dialog != null
                && loadingDialog!!.dialog.isShowing) {
            loadingDialog!!.dialog.dismiss()
        }
    }

    protected fun showLoadingDialog(stringResId: Int) {
        loadingDialog = LoadingDialog.newInstance(getString(stringResId),
                getString(R.string.please_wait), true)
        loadingDialog!!.show(activity!!.supportFragmentManager, null)
        loadingDialog!!.cancelListener = View.OnClickListener { cancelLoadingDialog() }
    }
}
