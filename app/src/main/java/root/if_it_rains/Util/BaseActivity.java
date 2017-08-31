package root.if_it_rains.Util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by root1 on 2017. 8. 21..
 */

public class BaseActivity extends AppCompatActivity {

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showSnack(String message, @Nullable String actionName, @Nullable  View.OnClickListener action){
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG);
        if (actionName != null){
            snackbar.setAction(actionName, action);
        }
        snackbar.show();
    }

    public void nextActivityWithFinish(Context context, Class cls){
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        finish();
    }

    private void setStatusBarColor(String colorCode){
        
    }



}
