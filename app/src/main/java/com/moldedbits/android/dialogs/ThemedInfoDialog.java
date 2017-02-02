package com.moldedbits.android.dialogs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.moldedbits.android.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by viveksingh on 18/01/16.
 */
public class ThemedInfoDialog extends DialogFragment {

    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TITLE = "title";
    public static final String KEY_POSITIVE_BUTTON_TEXT = "positive_button_text";
    private static final String KEY_NEGATIVE_BUTTON_TEXT = "negative_button_text";
    private static final String KEY_SHOW_CANCEL_BUTTON = "show_cancel_button";
    private ProgressDialog mProgressDialog;
    protected boolean mShowCancel = false;

    private ViewGroup mContainer;

    @Getter
    @Setter
    public View.OnClickListener okListener;

    @Bind(R.id.tv_title)
    TextView mTitle;

    @Bind(R.id.tv_message)
    TextView mMessage;

    @Bind(R.id.btn_ok)
    Button mOkButton;

    @Bind(R.id.btn_no)
    Button mCancelButton;
    boolean mButton;


    public static ThemedInfoDialog newInstance(String title, String message, String positiveText,
                                               String negativeText, boolean cancelButton) {
        Bundle bundle = createBundle(title, message, positiveText, negativeText, cancelButton);
        ThemedInfoDialog dialog = new ThemedInfoDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    protected static Bundle createBundle(String title, String message,
                                         String positiveButtonText, String negativeButtonText, boolean cancelButton) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_TITLE, title);
        args.putString(KEY_POSITIVE_BUTTON_TEXT, positiveButtonText);
        args.putString(KEY_NEGATIVE_BUTTON_TEXT, negativeButtonText);
        args.putBoolean(KEY_SHOW_CANCEL_BUTTON, cancelButton);

        return args;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Applying the theme
        setCancelable(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
        View rootView = inflater.inflate(R.layout.fragment_themed_dialog, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().setCanceledOnTouchOutside(true);
        Bundle args = getArguments();
        mMessage.setText(args.getString(KEY_MESSAGE));
        mTitle.setText(args.getString(KEY_TITLE));
        mCancelButton.setVisibility(View.GONE);
        mButton = args.getBoolean(KEY_SHOW_CANCEL_BUTTON);
        if (mButton) {
            mCancelButton.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(args.getString(KEY_POSITIVE_BUTTON_TEXT))) {
            mOkButton.setText(getString(android.R.string.ok));
        } else {
            mOkButton.setText(args.getString(KEY_POSITIVE_BUTTON_TEXT));
        }

        return rootView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        Map<String, Object> data = new HashMap<>(1);
        data.put("DialogFragment", this.getClass().getName());
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("DialogFragment", this.getClass().getName());
        return super.show(transaction, tag);
    }

    @OnClick(R.id.btn_ok)
    void onClickOk(View view) {
        dismiss();
        if (okListener != null) {
            okListener.onClick(view);
        }
    }


    @OnClick(R.id.btn_no)
    public void onClickCancel() {
        dismiss();
    }
}