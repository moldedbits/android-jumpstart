package com.moldedbits.android.dialogs

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.moldedbits.android.R


class LoadingDialog : android.support.v4.app.DialogFragment() {

    companion object {

        val TITLE = "title"
        val DESCRIPTION = "description"
        val SHOW_CANCEL_BUTTON = "showCancelButton"

        fun newInstance(title: String, description: String, button: Boolean): LoadingDialog {
            val loadingDialog = LoadingDialog()
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            bundle.putString(DESCRIPTION, description)
            bundle.putBoolean(SHOW_CANCEL_BUTTON, button)
            loadingDialog.arguments = bundle
            return loadingDialog
        }
    }

    protected var mbutton = true

    private var tvTitle: TextView? = null
    private var tvTitleDesc: TextView? = null

    var cancelListener: View.OnClickListener? = null
        set(cancelListener) {
            field = this.cancelListener
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.loading_dialog, container, true)

        tvTitle = view.findViewById<View>(R.id.tv_step) as TextView
        tvTitleDesc = view.findViewById<View>(R.id.tv_step_name) as TextView
        val btnCancel = view.findViewById<View>(R.id.btn_cancel) as Button

        btnCancel.setOnClickListener(this.cancelListener)


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (dialog.window != null) {
            val params = dialog.window!!.attributes
            params.height = WindowManager.LayoutParams.MATCH_PARENT
            params.width = WindowManager.LayoutParams.MATCH_PARENT

            dialog.window!!.attributes = params
        }

        mbutton = arguments!!.getBoolean(SHOW_CANCEL_BUTTON)
        if (!mbutton) {
            btnCancel.visibility = View.GONE
        }

        isCancelable = false
        updateInfo(arguments!!.getString(TITLE), arguments!!.getString(DESCRIPTION))

        return view
    }

    fun updateInfo(step: String?, stepInfo: String?) {
        if (tvTitle != null) {
            tvTitle!!.text = step
            tvTitleDesc!!.text = stepInfo
        }
    }
}