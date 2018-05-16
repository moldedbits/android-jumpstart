package com.moldedbits.android.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.moldedbits.android.R

/**
 * Created by viveksingh
 * on 18/01/16.
 */
class ThemedInfoDialog : DialogFragment() {

    companion object {

        private val KEY_MESSAGE = "message"
        private val KEY_TITLE = "title"
        private val KEY_POSITIVE_BUTTON_TEXT = "positive_button_text"
        private val KEY_NEGATIVE_BUTTON_TEXT = "negative_button_text"
        private val KEY_SHOW_CANCEL_BUTTON = "show_cancel_button"


        fun newInstance(title: String, message: String, positiveText: String,
                        negativeText: String, cancelButton: Boolean): ThemedInfoDialog {
            val bundle = createBundle(title, message, positiveText, negativeText, cancelButton)
            val dialog = ThemedInfoDialog()
            dialog.arguments = bundle
            return dialog
        }

        protected fun createBundle(title: String, message: String,
                                   positiveButtonText: String, negativeButtonText: String, cancelButton: Boolean): Bundle {
            val args = Bundle()
            args.putString(KEY_MESSAGE, message)
            args.putString(KEY_TITLE, title)
            args.putString(KEY_POSITIVE_BUTTON_TEXT, positiveButtonText)
            args.putString(KEY_NEGATIVE_BUTTON_TEXT, negativeButtonText)
            args.putBoolean(KEY_SHOW_CANCEL_BUTTON, cancelButton)

            return args
        }
    }

    var okListener: View.OnClickListener? = null

    @BindView(R.id.tv_title)
    internal var titleTv: TextView? = null

    @BindView(R.id.tv_message)
    internal var messageTv: TextView? = null

    @BindView(R.id.btn_ok)
    internal var okButton: Button? = null

    @BindView(R.id.btn_no)
    internal var cancelButton: Button? = null
    internal var button: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Applying the theme
        isCancelable = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_themed_dialog, container, false)
        ButterKnife.bind(this, rootView)
        dialog.setCanceledOnTouchOutside(true)
        val args = arguments
        messageTv!!.text = args!!.getString(KEY_MESSAGE)
        titleTv!!.text = args.getString(KEY_TITLE)
        cancelButton!!.visibility = View.GONE
        button = args.getBoolean(KEY_SHOW_CANCEL_BUTTON)
        if (button) {
            cancelButton!!.visibility = View.VISIBLE
        }

        if (TextUtils.isEmpty(args.getString(KEY_POSITIVE_BUTTON_TEXT))) {
            okButton!!.text = getString(android.R.string.ok)
        } else {
            okButton!!.text = args.getString(KEY_POSITIVE_BUTTON_TEXT)
        }

        return rootView
    }

    @OnClick(R.id.btn_ok)
    internal fun onClickOk(view: View) {
        dismiss()
        if (okListener != null) {
            okListener!!.onClick(view)
        }
    }

    @OnClick(R.id.btn_no)
    fun onClickCancel() {
        dismiss()
    }
}