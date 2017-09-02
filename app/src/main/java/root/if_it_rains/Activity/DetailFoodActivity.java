package root.if_it_rains.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class DetailFoodActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

//        NMapView mMapView = new NMapView(this);
//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.rootLayout);
//        linearLayout.addView(mMapView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        mMapView.setClientId("i0Mk4LqWppDoFS7EUNgK");
//        mMapView.setClickable(true);
//        mMapView.setEnabled(true);
//        mMapView.setFocusable(true);
//        mMapView.setFocusableInTouchMode(true);
//        mMapView.requestFocus();
//
//
//        Intent intent = getIntent();
//        FoodModel foodModel = (FoodModel)intent.getSerializableExtra("data");
//
//        TextView titleText, subTitleText;
//        titleText = (TextView)findViewById(R.id.titleText);
//        subTitleText = (TextView)findViewById(R.id.subTitleText);
//
//        titleText.setText(foodModel.getName());
//        subTitleText.setText(foodModel.getTel());
//
//        Log.d("xxx", "onCreate: " + Double.parseDouble(foodModel.getLati()) + "/" + Double.parseDouble(foodModel.getLongi()));
        //tMapView.setCenterPoint(100.1, 100.1);
    }
}
