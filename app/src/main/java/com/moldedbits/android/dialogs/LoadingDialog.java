package com.moldedbits.android.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.moldedbits.android.R;

import lombok.Getter;
import lombok.Setter;


public class LoadingDialog extends android.support.v4.app.DialogFragment {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private static final String SHOW_CANCEL_BUTTON = "showCancelButton";
    protected boolean mbutton = true;

    private TextView tvTitle;
    private TextView tvTitleDesc;
    private Button btnCancel;

    @Getter
    @Setter
    private View.OnClickListener cancelListener;

    public static LoadingDialog newInstance(String title, String description, boolean button) {
        final LoadingDialog loadingDialog = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(DESCRIPTION, description);
        bundle.putBoolean(SHOW_CANCEL_BUTTON, button);
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_dialog, container, true);

        tvTitle = (TextView) view.findViewById(R.id.tv_step);
        tvTitleDesc = (TextView) view.findViewById(R.id.tv_step_name);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener((cancelListener));


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;

        getDialog().getWindow().setAttributes(params);

        mbutton = getArguments().getBoolean(SHOW_CANCEL_BUTTON);
        if (!mbutton) {
            btnCancel.setVisibility(View.GONE);
        }

        setCancelable(false);
        updateInfo(getArguments().getString(TITLE), getArguments().getString(DESCRIPTION));

        return view;
    }

    public void updateInfo(String step, String stepInfo) {
        if (tvTitle != null) {
            tvTitle.setText(step);
            tvTitleDesc.setText(stepInfo);
        }
    }
}