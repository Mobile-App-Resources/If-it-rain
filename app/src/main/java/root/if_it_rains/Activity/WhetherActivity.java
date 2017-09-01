package root.if_it_rains.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import root.if_it_rains.Model.WhetherModel;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 8. 31..
 */

public class WhetherActivity extends BaseActivity {

    TextView monthText, dateText, adressText, temText, hignTemText, lowTemText, humidityText;
    LinearLayout progressLayout, rootLayout;
    ImageView temUpImage, temDownImage;
    TextView infoText;

    TextView timeText1, timeText2, timeText3, timeText4, timeText5, timeText6, whetherText;
    ImageView whetherImage;

    TextView textViewArr[];
    TextView timeTextArr[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        progressLayout = (LinearLayout)findViewById(R.id.dateProgressLayout);
        rootLayout = (LinearLayout)findViewById(R.id.rootLayout);

        //whetherText = (TextView)findViewById(R.id.whetherText);
        whetherImage = (ImageView)findViewById(R.id.whetherImage);

        temUpImage = (ImageView)findViewById(R.id.temUpImage);
        temDownImage = (ImageView)findViewById(R.id.temDownImage);

        infoText = (TextView)findViewById(R.id.infoText);

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

        textViewArr = new TextView[]{monthText, dateText, adressText, temText, lowTemText, hignTemText, humidityText, infoText};
        timeTextArr = new TextView[]{timeText1, timeText2, timeText3, timeText4, timeText5, timeText6};

        Intent intent = getIntent();
        WhetherModel whe = (WhetherModel)intent.getSerializableExtra("whe");

        setDataToLayout(whe);
        setImageToLayout(whe);
        setTextColor(whe);

    }

    private TextView setTimeProgress(int time, int code){
        for(TextView tv : timeTextArr){
            tv.setVisibility(View.INVISIBLE);
        }

        Log.d("xxx", "setTimeProgress: " + time);

        int size = time/4 + 1;
        Log.d("xxx", "" + size);

        timeTextArr[size - 1].setVisibility(View.VISIBLE);

        View view = new View(this);
        View view2 = new View(this);
        if(code % 2 == 1){
            view.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            view2.setBackgroundColor(getResources().getColor(R.color.colorArrowBlack));
        }else{
            view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            view2.setBackgroundColor(getResources().getColor(R.color.colorArrowWhite));
        }

        progressLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, size));
        progressLayout.addView(view2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6 - size));
        return timeTextArr[size - 1];
    }

    private void setDataToLayout(WhetherModel whe){
        DateFommatClass tempDate = setDateFommat(whe.getUpdate());
        monthText.setText(tempDate.month);
        dateText.setText(tempDate.date);
        setTimeProgress(tempDate.hour, whe.getCode()).setText(tempDate.time);
        adressText.setText(whe.getRegion());
        temText.setText(whe.getTc() + "℃");
        hignTemText.setText(whe.getTmax() + "℃");
        lowTemText.setText(whe.getTmin() + "℃");
        humidityText.setText(whe.getHumidity() + "%");
    }

    private void setTextColor(WhetherModel whe){
        if(whe.getCode() % 2 == 1){
            for(TextView tv : textViewArr){
                tv.setTextColor(getResources().getColor(R.color.colorTextBlack));
            }
            for(TextView tv : timeTextArr){
                tv.setTextColor(getResources().getColor(R.color.colorTextBlack));
            }
            temUpImage.setImageResource(R.drawable.whether_up_black);
            temDownImage.setImageResource(R.drawable.whether_down_black);
        }else{
            for(TextView tv : textViewArr){
                tv.setTextColor(getResources().getColor(R.color.colorTextWhite));
            }
            for(TextView tv : timeTextArr){
                tv.setTextColor(getResources().getColor(R.color.colorTextWhite));
            }
            temUpImage.setImageResource(R.drawable.whether_up_white);
            temDownImage.setImageResource(R.drawable.whether_down_white);
        }
    }

    private void setImageToLayout(WhetherModel whe){
        //맑음 0, 흐림 1, 비 2, 눈 3, 낙뢰 4
        int backWhetherImage[] = {R.drawable.back_sun, R.drawable.back_cloud, R.drawable.back_rain, R.drawable.back_snow, R.drawable.back_thun};
        int whetherIcon[] = {R.drawable.icon_sun, R.drawable.icon_cloud, R.drawable.icon_rain, R.drawable.icon_snow, R.drawable.icon_thun};
        whetherImage.setImageResource(whetherIcon[whe.getCode()]);
        rootLayout.setBackground(getDrawable(backWhetherImage[whe.getCode()]));
        //whetherText.setText(whe.getName());
    }

    private DateFommatClass setDateFommat(String date){
        final String monthStrArr[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        DateFommatClass dateFommatClass = new DateFommatClass();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dateData = sm.parse(date);
            Log.d("xxx", "setDateFommat: " + date);
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
