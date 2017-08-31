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
    @SerializedName("tc")
    private String tc;
    @SerializedName("tmax")
    private String tmax;
    @SerializedName("tmin")
    private String tmin;
    @SerializedName("sky")
    private Sky sky;

    class Sky{
        @SerializedName("code")
        String code;
        @SerializedName("name")
        String name;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public WhetherModel.Sky getSky() {
        return sky;
    }

    public void setSky(WhetherModel.Sky sky) {
        this.sky = sky;
    }

    public String getTmax() {
        return tmax;
    }

    public void setTmax(String tmax) {
        this.tmax = tmax;
    }

    public String getTmin() {
        return tmin;
    }

    public void setTmin(String tmin) {
        this.tmin = tmin;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}
