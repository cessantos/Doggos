package cess.com.br.doggos.models.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doggo {
    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("list")
    @Expose
    private List<String> doggoPictureList;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getDoggoPictureList() {
        return doggoPictureList;
    }

    public void setDoggoPictureList(List<String> doggoPictureList) {
        this.doggoPictureList = doggoPictureList;
    }
}
