package cess.com.br.doggos.models.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    @Expose
    private UserContent user;

    public UserContent getUser() {
        return user;
    }

    public void setUser(UserContent user) {
        this.user = user;
    }
}

