package root.if_it_rains.Activity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import root.if_it_rains.Connect.RetrofitClass;
import root.if_it_rains.Model.MachineModel;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Token", "" + FirebaseInstanceId.getInstance().getToken());

        RetrofitClass.getInstance().apiInterface.getMachineData(FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<MachineModel>() {
            @Override
            public void onResponse(Call<MachineModel> call, Response<MachineModel> response) {
                if(response.code() == 204){

                }else if(response.code() == 200){

                }
            }

            @Override
            public void onFailure(Call<MachineModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
