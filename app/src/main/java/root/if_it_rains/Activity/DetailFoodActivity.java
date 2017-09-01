package root.if_it_rains.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skp.Tmap.TMapView;

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

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.rootLayout);
        linearLayout.addView(tMapView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
