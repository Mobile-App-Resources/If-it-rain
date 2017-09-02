package root.if_it_rains.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import root.if_it_rains.Model.FoodModel;
import root.if_it_rains.Model.ListForBundle;
import root.if_it_rains.Model.PoemModel;
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

        Intent intent = getIntent();
        ListForBundle foodList = (ListForBundle)intent.getSerializableExtra("food");
        ListForBundle poemList = (ListForBundle)intent.getSerializableExtra("poem");
        Log.d("xxx", "onCreate: " + foodList + poemList);

        foodViewPager = (ViewPager)findViewById(R.id.foodViewPager);
        poemViewPager = (ViewPager)findViewById(R.id.poemViewPager);
        foodViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), true, foodList));
        poemViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), false, poemList));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private boolean isFood;
        private List list;

        public ViewPagerAdapter(FragmentManager fm, boolean isFood, ListForBundle listForBundle) {
            super(fm);
            this.isFood = isFood;
            list = listForBundle.getList();
        }

        @Override
        public Fragment getItem(int position) {
            return new InfoCardFragment(isFood, list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}

class InfoCardFragment extends Fragment implements View.OnClickListener{

    private boolean isFood;
    private Object object;

    public InfoCardFragment(boolean isFood, Object object) {
        this.isFood = isFood;
        this.object = object;
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
        imageView = (ImageView)view.findViewById(R.id.imageView);

        if(isFood){
            categoryText.setText("음식점");
            final FoodModel foodModel = (FoodModel)object;
            imageView.setImageResource(R.drawable.icon_food_round);
            titleText.setText(foodModel.getName());
            subTitleText.setText(foodModel.getTel());
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + foodModel.getTel()));
                    startActivity(intent);
                }
            });
        }else{
            categoryText.setText("시");
            imageView.setImageResource(R.drawable.icon_poem_round);
            callButton.setVisibility(View.GONE);
            PoemModel poemModel = (PoemModel)object;
            titleText.setText(poemModel.getTitle());
            subTitleText.setText(poemModel.getWriter());
        }

        view.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(isFood){
            //Intent intent = new Intent(getContext(), DetailFoodActivity.class);
            //Bundle bundle = new Bundle();
            //bundle.putSerializable("data", (FoodModel)object);
            //intent.putExtras(bundle);
            //startActivity(intent);
        }else{
            Intent intent = new Intent(getContext(), PoemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", (PoemModel)object);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}


