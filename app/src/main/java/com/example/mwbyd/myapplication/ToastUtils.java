package com.example.mwbyd.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastUtils {

    protected static Toast toast = null;

    private static volatile ToastUtils mToastUtils;

    private ToastUtils(Context context) {
        toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
    }

    public static ToastUtils getInstance(Context context) {
        if (null == mToastUtils) {
            synchronized (ToastUtils.class) {
                if (null == mToastUtils) {
                    mToastUtils = new ToastUtils(context);
                }
            }
        }
        return mToastUtils;
    }

    public void showMessage(int toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void showMessage(String toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void toastCancel() {
        if (null != toast) {
            toast.cancel();
            toast = null;
        }
        mToastUtils = null;
    }

}
