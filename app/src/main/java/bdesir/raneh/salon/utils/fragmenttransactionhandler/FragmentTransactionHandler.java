package bdesir.raneh.salon.utils.fragmenttransactionhandler;

import android.os.Message;
import android.support.v4.app.FragmentActivity;

import bdesir.raneh.salon.dialogs.ThemedInfoDialog;

public class FragmentTransactionHandler extends PauseHandler {

    private FragmentActivity activity;
    public static final int LOADING_DIALOG_DISMISS_MSG = 100002;
    public static final int SHOW_THEMED_INFO_DIALOG = 100003;

    /**
     * Set the activity associated with the handler
     *
     * @param activity the activity to set
     */
    public final void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    /**
     * Notification that the message is about to be stored as the activity is
     * paused. If not handled the message will be saved and replayed when the
     * activity resumes.
     *
     * @param message the message which optional can be handled
     * @return true if the message is to be stored
     */
    @Override
    protected boolean storeMessage(Message message) {
        return true;
    }

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message the message to be handled
     */
    @Override
    protected void processMessage(Message message) {
        if (activity != null) {
            switch (message.what) {
                case SHOW_THEMED_INFO_DIALOG:
                    ThemedInfoDialog dialog = (ThemedInfoDialog) message.obj;
                    dialog.show(activity.getSupportFragmentManager(), "themedInfoDialog");
                    break;
                default:
                    // do nothing
                    break;
            }
        }
    }
}