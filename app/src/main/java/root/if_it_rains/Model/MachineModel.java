package root.if_it_rains.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class MachineModel {
    @SerializedName("code")
    private String modelCode;

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
}
