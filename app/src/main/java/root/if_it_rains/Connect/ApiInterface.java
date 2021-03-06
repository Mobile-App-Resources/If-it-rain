package root.if_it_rains.Connect;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;
import root.if_it_rains.Model.MachineModel;
import root.if_it_rains.Model.WhetherModel;

/**
 * Created by root1 on 2017. 8. 23..
 */

public interface ApiInterface {

    @GET("/auth/weather")
    Call<WhetherModel> getWhetherData(@Query("lati")String lati, @Query("longi")String longi);

    @GET("/auth/open")
    Call<MachineModel> getMachineData(@Query("token")String token);

    @GET("/auth/add")
    Call<Void> setMachineData(@Query("code")String code, @Query("token")String token);

    @DELETE("/auth/delete")
    Call<Void> deleteMachineData(@Query("token")String token);

    @GET("/auth/search")
    Call<JsonArray> getInfoData(@Query("lati")String lati, @Query("longi")String longi);
}
