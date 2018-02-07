package bdesir.raneh.salon.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bdesir.raneh.salon.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by viveksingh
 * on 18/01/16.
 */
public class ThemedInfoDialog extends DialogFragment {

    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TITLE = "title";
    private static final String KEY_POSITIVE_BUTTON_TEXT = "positive_button_text";
    private static final String KEY_NEGATIVE_BUTTON_TEXT = "negative_button_text";
    private static final String KEY_SHOW_CANCEL_BUTTON = "show_cancel_button";

    @Getter
    @Setter
    public View.OnClickListener okListener;

    @BindView(R.id.tv_title)
    TextView titleTv;

    @BindView(R.id.tv_message)
    TextView messageTv;

    @BindView(R.id.btn_ok)
    Button okButton;

    @BindView(R.id.btn_no)
    Button cancelButton;
    boolean button;


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
        View rootView = inflater.inflate(R.layout.fragment_themed_dialog, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().setCanceledOnTouchOutside(true);
        Bundle args = getArguments();
        messageTv.setText(args.getString(KEY_MESSAGE));
        titleTv.setText(args.getString(KEY_TITLE));
        cancelButton.setVisibility(View.GONE);
        button = args.getBoolean(KEY_SHOW_CANCEL_BUTTON);
        if (button) {
            cancelButton.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(args.getString(KEY_POSITIVE_BUTTON_TEXT))) {
            okButton.setText(getString(android.R.string.ok));
        } else {
            okButton.setText(args.getString(KEY_POSITIVE_BUTTON_TEXT));
        }

        return rootView;
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