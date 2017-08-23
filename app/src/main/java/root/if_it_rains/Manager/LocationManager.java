package root.if_it_rains.Manager;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by root1 on 2017. 8. 21..
 */

public class LocationManager {

    @SuppressWarnings("MissingPermission")
    public void getCurrentLocation(Context context){
        OnCompleteListener<Location> getLocationCompleteListener = new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful() && task.getResult() != null){
                    Log.d("location", task.getResult().getLatitude() + "/" + task.getResult().getLongitude());
                }else{
                    Log.d("locationExeption", task.getException().getMessage());
                }
            }
        };

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(context);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(getLocationCompleteListener);
    }

}
