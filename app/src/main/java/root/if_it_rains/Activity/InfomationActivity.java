package root.if_it_rains.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class InfomationActivity extends BaseActivity {

    ViewPager foodViewPager, poemViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        foodViewPager = (ViewPager)findViewById(R.id.foodViewPager);
        poemViewPager = (ViewPager)findViewById(R.id.poemViewPager);
        foodViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), true));
        poemViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), false));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private boolean isFood;
        public ViewPagerAdapter(FragmentManager fm, boolean isFood) {
            super(fm);
            this.isFood = isFood;
        }

        @Override
        public Fragment getItem(int position) {
            return new InfoCardFragment(isFood);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}

class InfoCardFragment extends Fragment implements View.OnClickListener{

    private boolean isFood;

    public InfoCardFragment(boolean isFood) {
        this.isFood = isFood;
    }

    private TextView titleText, subTitleText, categoryText;
    private ImageView imageView;
    private ImageButton callButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_card, container, false);
        titleText = (TextView)view.findViewById(R.id.titleText);
        subTitleText = (TextView)view.findViewById(R.id.subTitleText);
        categoryText = (TextView)view.findViewById(R.id.categoryText);
        callButton = (ImageButton)view.findViewById(R.id.callButton);

        if(isFood){
            categoryText.setText("음식점");
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else{
            categoryText.setText("시");
            callButton.setVisibility(View.GONE);
        }

        view.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
