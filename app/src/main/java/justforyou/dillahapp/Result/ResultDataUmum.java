package justforyou.dillahapp.Result;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import justforyou.dillahapp.Model.DataUmum;

public class ResultDataUmum {
    @SerializedName("data")
    @Expose
    private List<DataUmum> ResultDataUmum = null;

    public List<DataUmum> getData() {
        return ResultDataUmum;
    }

    public void setData(List<DataUmum> ResultDataUmum) {
        this.ResultDataUmum = ResultDataUmum;
    }
}

