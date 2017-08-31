package root.if_it_rains.Connect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import root.if_it_rains.Model.WhetherModel;

/**
 * Created by root1 on 2017. 8. 23..
 */

public interface ApiInterface {

    @GET("/auth/weather")
    Call<WhetherModel> getWhetherDate(@Query("rati")String lati, @Query("longi")String longi);
}
