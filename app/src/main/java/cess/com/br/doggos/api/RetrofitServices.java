package cess.com.br.doggos.api;

import java.util.Map;

import cess.com.br.doggos.models.remote.Doggo;
import cess.com.br.doggos.models.remote.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitServices {

    @POST("signup")
    Observable<User> requestJson2(@Body Map<String, String> body);

    @GET("feed")
    Observable<Doggo> getDoggosByCategory(@Header("Authorization") String token, @Query("category") String category);
}
