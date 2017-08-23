package root.if_it_rains.Activity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Token", "" + FirebaseInstanceId.getInstance().getToken());
    }
}
