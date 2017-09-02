package root.if_it_rains.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import root.if_it_rains.Connect.RetrofitClass;
import root.if_it_rains.Model.MachineModel;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    Button setButton;
    ImageView imageView;
    TextView contentText;
    EditText codeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton = (Button)findViewById(R.id.setButton);
        setButton.setOnClickListener(this);
        imageView = (ImageView)findViewById(R.id.imageView);
        contentText = (TextView)findViewById(R.id.contentText);
        codeEdit = (EditText)findViewById(R.id.codeEdit);



        Log.d("Token", "" + FirebaseInstanceId.getInstance().getToken());

        RetrofitClass.getInstance().apiInterface.getMachineData(FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<MachineModel>() {
            @Override
            public void onResponse(Call<MachineModel> call, Response<MachineModel> response) {
                if(response.code() == 204){
                    noData();
                }else if(response.code() == 200){
                    yesData(response.body().getModelCode());
                }
            }

            @Override
            public void onFailure(Call<MachineModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void noData(){
        codeEdit.setText("");
        setButton.setText("기기 추가하기");
        contentText.setText(getString(R.string.main_info));
        codeEdit.setVisibility(View.VISIBLE);
        imageView.setBackgroundResource(R.drawable.main_image_shape);
    }

    private void yesData(String code){
        setButton.setText("기기 삭제하기");
        contentText.setText(code);
        codeEdit.setVisibility(View.GONE);
        imageView.setBackgroundResource(R.drawable.main_image_shape_green);
    }

    @Override
    public void onClick(View view) {
        if(setButton.getText().toString().equals("기기 추가하기")){
            if(codeEdit.getText().toString().isEmpty()){
                showToast("코드를 입력하십시오");
            }else{
                RetrofitClass.getInstance().apiInterface.setMachineData(codeEdit.getText().toString(),FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 204){
                            yesData(codeEdit.getText().toString());
                        }else{
                            showToast("등록 오류" + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }else{
            RetrofitClass.getInstance().apiInterface.deleteMachineData(FirebaseInstanceId.getInstance().getToken()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.code() == 204){
                        noData();
                    }else{
                        showToast("삭제 오류");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
