package com.example.jopy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;

import org.jetbrains.annotations.NotNull;

public class UtilFunctions {
    public static LottieAlertDialog alertDialog ;
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public static void showProgressBar(Activity activity) {
        alertDialog= new LottieAlertDialog.Builder(activity, DialogTypes.TYPE_LOADING)
                .setTitle("Loading")
                .setDescription("Please Wait")
                .build();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void hideProgressBar() {
        alertDialog.dismiss() ;
    }

    public static void saveLocal(String key, String value, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Joby" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =   sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
        Log.e("1", "saveLocal: "+value);
    }

    public static String readLocal(String key,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Joby" , Context.MODE_PRIVATE);
        Log.e("2", "readLocal: "+sharedPreferences.getString("token",""));
        return sharedPreferences.getString(key,"");
    }
}
