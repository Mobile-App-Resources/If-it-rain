package root.if_it_rains.Manager;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import root.if_it_rains.Activity.InfomationActivity;
import root.if_it_rains.Connect.RetrofitClass;
import root.if_it_rains.Model.FoodModel;
import root.if_it_rains.Model.ListForBundle;
import root.if_it_rains.Model.PoemModel;

/**
 * Created by root1 on 2017. 8. 21..
 */

public class FirebaseMessagingManagerService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("xxx", "onMessageReceived: " + remoteMessage.getNotification().getBody());

        setInfoData();
    }

    private void setInfoData(){
        LocationManager.getInstance().getCurrentLocation(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful() && task.getResult() != null){
                    String lati = "", longi = "";
                    lati += task.getResult().getLatitude();
                    longi += task.getResult().getLongitude();
                    Log.d("xxx", "onComplete: " + lati + "/" + longi);
                    RetrofitClass.getInstance().apiInterface.getInfoData(lati, longi).enqueue(new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            JsonArray jsonArray = response.body();
                            JsonElement jsonElementFood = jsonArray.get(0).getAsJsonObject().get("food");
                            JsonElement jsonElementPoem = jsonArray.get(1).getAsJsonObject().get("writer");
                            Gson gson = new Gson();
                            FoodModel foodModelArr[] = gson.fromJson(jsonElementFood, FoodModel[].class);
                            List<FoodModel> foodList = Arrays.asList(foodModelArr);

                            PoemModel poemModelArr[] = gson.fromJson(jsonElementPoem, PoemModel[].class);
                            List<PoemModel> poemList = Arrays.asList(poemModelArr);
                            Log.d("xxx", "onResponse: " + foodList + poemList);

                            Intent intent = new Intent(FirebaseMessagingManagerService.this, InfomationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("food", new ListForBundle(foodList));
                            bundle.putSerializable("poem", new ListForBundle(poemList));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }else{
                    Log.d("locationExeption", task.getException().getMessage());
                }
            }
        });
    }
}
