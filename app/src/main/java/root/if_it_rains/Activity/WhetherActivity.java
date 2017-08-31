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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        progressLayout = (LinearLayout)findViewById(R.id.dateProgressLayout);

        monthText = (TextView)findViewById(R.id.monthText);
        dateText = (TextView)findViewById(R.id.dateText);
        adressText = (TextView)findViewById(R.id.adressText);
        temText = (TextView)findViewById(R.id.temText);
        hignTemText = (TextView)findViewById(R.id.highTem);
        lowTemText = (TextView)findViewById(R.id.lowTem);
        humidityText = (TextView)findViewById(R.id.humidityText);

        timeText1 = (TextView)findViewById(R.id.timeText1);
        timeText2 = (TextView)findViewById(R.id.timeText2);
        timeText3 = (TextView)findViewById(R.id.timeText3);
        timeText4 = (TextView)findViewById(R.id.timeText4);
        timeText5 = (TextView)findViewById(R.id.timeText5);
        timeText6 = (TextView)findViewById(R.id.timeText6);

        getWhetherData();

    }

    private TextView setTimeProgress(int time){
        TextView timeTextArr[] = {timeText1,timeText2,timeText3,timeText4,timeText5,timeText6};
        for(TextView tv : timeTextArr){
            tv.setVisibility(View.INVISIBLE);
        }

        int size = time/4;
        timeTextArr[size - 1].setVisibility(View.VISIBLE);
        View view = new View(this);
        View view2 = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        view2.setBackgroundColor(getResources().getColor(R.color.colorArrowWhite));
        progressLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, size));
        progressLayout.addView(view2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6 - size));

        return timeTextArr[time/4 - 1];
    }

    private void getWhetherData(){
        LocationManager.getInstance().getCurrentLocation(this, getLocationFunc);
    }

    private void setDataToLayout(WhetherModel whe){
        DateFommatClass tempDate = setDateFommat(whe.getUpdate());
        monthText.setText(tempDate.month);
        dateText.setText(tempDate.date);
        setTimeProgress(tempDate.hour).setText(tempDate.time);
        adressText.setText(whe.getRegion());
        temText.setText(whe.getTc() + "℃");
        hignTemText.setText(whe.getTmax() + "℃");
        lowTemText.setText(whe.getTmin() + "℃");
        humidityText.setText(whe.getHumidity() + "%");
    }

    private void setImageToLayout(WhetherModel whe){

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
                        Log.d("Xxx", "onResponse: " + "hello");
                        WhetherModel whe = response.body();
                        setDataToLayout(whe);
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

    private DateFommatClass setDateFommat(String date){
        final String monthStrArr[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        DateFommatClass dateFommatClass = new DateFommatClass();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dateData = sm.parse(date);
            dateFommatClass.month = monthStrArr[dateData.getMonth() - 1];
            dateFommatClass.date = dateData.getDate() + "th";
            dateFommatClass.time = dateData.getHours() + " : 00";
            dateFommatClass.hour = dateData.getHours();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFommatClass;
    }

    class DateFommatClass{
        String month = "", date = "", time = "";
        int hour;
    }
}
