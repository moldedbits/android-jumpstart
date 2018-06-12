package com.moldedbits.android.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.moldedbits.android.R;

import lombok.Getter;
import lombok.Setter;


public class LoadingDialog extends android.support.v4.app.DialogFragment {

    public static final String DESCRIPTION = "description";

    @Getter
    @Setter
    private View.OnClickListener cancelListener;
    private TextView tvInfo;

    public static LoadingDialog newInstance(String description) {
        final LoadingDialog loadingDialog = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putString(DESCRIPTION, description);
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_dialog, container, true);
        tvInfo = view.findViewById(R.id.tv_info);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;

            getDialog().getWindow().setAttributes(params);
        }

        setCancelable(false);

        return view;
    }

    public void updateInfo(String info) {
        tvInfo.setText(info);
    }
}