package root.if_it_rains.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root1 on 2017. 8. 30..
 */

public class WhetherModel {
    @SerializedName("update")
    private String update;
    @SerializedName("region")
    private String region;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("Tc")
    private Tc tc;
    @SerializedName("sky")
    private sky sky;

    class Tc{
        @SerializedName("tc")
        String tc;
        @SerializedName("tmax")
        String tmax;
        @SerializedName("tmin")
        String tmin;
    }

    class sky{
        @SerializedName("code")
        String code;
        @SerializedName("name")
        String name;
    }
}
