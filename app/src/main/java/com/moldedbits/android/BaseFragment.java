package com.moldedbits.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.moldedbits.android.dialogs.LoadingDialog;
import com.moldedbits.android.utils.fragment_transaction_handler.FragmentTransactionHandler;

// TODO: 05/04/16 Add Loader Implementation
public class BaseFragment extends Fragment {

    public FragmentTransactionHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new FragmentTransactionHandler();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //enabling toolbar to have menu items in fragment
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.setActivity(getActivity());
        handler.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        handler.setActivity(null);
    }

    public FragmentTransactionHandler getHandler() {
        return handler;
    }

    private LoadingDialog mLoadingDialog;

    protected void cancelLoadingDialog() {
        if (mLoadingDialog != null
                && mLoadingDialog.getDialog() != null
                && mLoadingDialog.getDialog().isShowing()) {
            mLoadingDialog.getDialog().dismiss();
        }
    }

    protected void showLoadingDialog(int stringResId) {
        mLoadingDialog = LoadingDialog.newInstance(getString(stringResId),
                getString(R.string.please_wait), true);
        mLoadingDialog.show(getActivity().getSupportFragmentManager(), null);
        mLoadingDialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelLoadingDialog();
            }
        });
    }
}
