package root.if_it_rains.Manager;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by root1 on 2017. 8. 21..
 */

public class LocationManager {

    private LocationManager(){}

    private static LocationManager locationManager = new LocationManager();

    public static LocationManager getInstance(){
        return locationManager;
    }


    @SuppressWarnings("MissingPermission")
    public void getCurrentLocation(Context context, OnCompleteListener<Location> getLocationCompleteListener){
        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(context);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(getLocationCompleteListener);
    }

}
