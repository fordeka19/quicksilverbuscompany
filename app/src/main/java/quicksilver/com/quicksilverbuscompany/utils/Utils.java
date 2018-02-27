package quicksilver.com.quicksilverbuscompany.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

public class Utils {

    public static void showDialog(@NonNull Context context, int msgId, int buttonId, DialogInterface.OnClickListener buttonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(context.getString(msgId))
                .setPositiveButton(context.getString(buttonId), buttonListener);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        builder.show();
    }
}
