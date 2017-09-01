package root.if_it_rains.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skp.Tmap.TMapView;

import root.if_it_rains.Model.FoodModel;
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

        TMapView tMapView = new TMapView(this);
        tMapView.setSKPMapApiKey("ae1f4f88-341c-3d74-a0bb-324a3d4fd36b");
        Intent intent = getIntent();
        FoodModel foodModel = (FoodModel)intent.getSerializableExtra("data");

        TextView titleText, subTitleText;
        titleText = (TextView)findViewById(R.id.titleText);
        subTitleText = (TextView)findViewById(R.id.subTitleText);

        titleText.setText(foodModel.getName());
        subTitleText.setText(foodModel.getTel());

        try{
            tMapView.setCenterPoint(Double.parseDouble(foodModel.getLati()), Double.parseDouble(foodModel.getLongi()));
        }catch (Exception e){
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.rootLayout);
        linearLayout.addView(tMapView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
