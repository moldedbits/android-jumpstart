package com.moldedbits.android;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.moldedbits.android.api.ApiService;
import com.moldedbits.android.dialogs.LoadingDialog;
import com.moldedbits.android.utils.fragmenttransactionhandler.FragmentTransactionHandler;

import javax.inject.Inject;


/**
 * Created by shishank on 08/01/16.
 */
// TODO: 05/04/16 Add Loader
public class BaseActivity extends AppCompatActivity {

    @Inject
    ApiService apiService;

    private FrameLayout contentFrame;
    public FragmentTransactionHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        BaseApplication.getInstance().getApiComponent().inject(this);

        contentFrame = (FrameLayout) findViewById(R.id.base_container);
        handler = new FragmentTransactionHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.setActivity(this);
        handler.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.setActivity(null);
    }

    public FragmentTransactionHandler getHandler() {
        return handler;
    }

    @Override
    public void setContentView(int layoutResId) {
        getLayoutInflater().inflate(layoutResId, contentFrame, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dismissLoadingDialogWithHandler(LoadingDialog dialog) {
        Message msg
                = getHandler().obtainMessage(FragmentTransactionHandler.LOADING_DIALOG_DISMISS_MSG,
                dialog);
        getHandler().sendMessage(msg);
    }

}
