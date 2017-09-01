package root.if_it_rains.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root1 on 2017. 9. 1..
 */

@SuppressWarnings("serial")
public class FoodModel implements Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("lati")
    private String lati;
    @SerializedName("longi")
    private String longi;
    @SerializedName("name")
    private String name;
    @SerializedName("tel")
    private String tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
