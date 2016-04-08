package com.moldedbits.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.moldedbits.android.api.ResponseCallback;
import com.moldedbits.android.dialogs.LoadingDialog;
import com.moldedbits.android.utils.LoaderHandler;
import com.moldedbits.android.utils.fragment_transaction_handler.FragmentTransactionHandler;

public abstract class BaseFragment extends Fragment
        implements android.support.v4.app.LoaderManager.LoaderCallbacks<Object>{

    public FragmentTransactionHandler handler;

    public abstract void requestApi(ResponseCallback responseCallback);

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

    @Override
    public android.support.v4.content.Loader<Object> onCreateLoader(int id, Bundle args) {
        return new LoaderHandler<Object>(getActivity()) {
            @Override
            public Object getData() {
                requestApi(getCallback());
                return null;
            }
        };
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
