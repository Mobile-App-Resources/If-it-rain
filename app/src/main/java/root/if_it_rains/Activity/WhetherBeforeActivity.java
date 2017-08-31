package root.if_it_rains.Activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import root.if_it_rains.Connect.RetrofitClass;
import root.if_it_rains.Manager.LocationManager;
import root.if_it_rains.Model.WhetherModel;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class WhetherBeforeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether_before);

        LocationManager.getInstance().getCurrentLocation(this, getLocationFunc);
    }

    OnCompleteListener<Location> getLocationFunc = new OnCompleteListener<Location>() {
        @Override
        public void onComplete(@NonNull Task<Location> task) {
            if(task.isSuccessful() && task.getResult() != null){
                String lati = "", longi = "";
                lati += task.getResult().getLatitude();
                longi += task.getResult().getLongitude();
                Log.d("location", lati + "/" + longi);
                RetrofitClass.getInstance().apiInterface.getWhetherData(lati,longi).enqueue(new Callback<WhetherModel>() {
                    @Override
                    public void onResponse(Call<WhetherModel> call, Response<WhetherModel> response) {
                        WhetherModel whe = response.body();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("whe", whe);
                        nextActivityWithFinish(WhetherBeforeActivity.this, WhetherActivity.class, bundle);
                    }

                    @Override
                    public void onFailure(Call<WhetherModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }else{
                Log.d("locationExeption", task.getException().getMessage());
            }
        }
    };
}
