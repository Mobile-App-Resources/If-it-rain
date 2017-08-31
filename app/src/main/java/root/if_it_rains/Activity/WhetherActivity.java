package root.if_it_rains.Activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * Created by root1 on 2017. 8. 31..
 */

public class WhetherActivity extends BaseActivity {

    TextView monthText, dateText, adressText, temText, hignTemText, lowTemText, humidityText;
    LinearLayout progressLayout;

    TextView timeText1, timeText2, timeText3, timeText4, timeText5, timeText6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        View view = new View(this);
        View view2 = new View(this);

        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        view2.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        progressLayout = (LinearLayout)findViewById(R.id.dateProgressLayout);

        progressLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3.0f));
        progressLayout.addView(view2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3.0f));

        getWhetherData();

    }

    private void setTimeProgress(String time){

    }

    private void getWhetherData(){
        LocationManager.getInstance().getCurrentLocation(this, getLocationFunc);
    }

    OnCompleteListener<Location> getLocationFunc = new OnCompleteListener<Location>() {
        @Override
        public void onComplete(@NonNull Task<Location> task) {
            if(task.isSuccessful() && task.getResult() != null){
                String lati = "", longi = "";
                lati += task.getResult().getLatitude();
                longi += task.getResult().getLongitude();
                Log.d("location", task.getResult().getLatitude() + "/" + task.getResult().getLongitude());
                RetrofitClass.getInstance().apiInterface.getWhetherDate(lati,longi).enqueue(new Callback<WhetherModel>() {
                    @Override
                    public void onResponse(Call<WhetherModel> call, Response<WhetherModel> response) {
                        Log.d("xxx", "onResponse: "+call);
                    }

                    @Override
                    public void onFailure(Call<WhetherModel> call, Throwable t) {

                    }
                });
            }else{
                Log.d("locationExeption", task.getException().getMessage());
            }
        }
    };
}
