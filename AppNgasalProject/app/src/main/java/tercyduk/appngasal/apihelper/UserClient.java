package tercyduk.appngasal.apihelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tercyduk.appngasal.coresmodel.LapanganFutsal;
import tercyduk.appngasal.coresmodel.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by User on 12/8/2017.
 */

public interface UserClient {
    @POST("login")
    Call<User> login(@Body User user);


    @POST("user/edit/")
    @FormUrlEncoded
    Call<User> find(@Header("Authorization") String authToken,@Field("email") String email);

    @PUT("user/update/")
    @FormUrlEncoded
    Call<Boolean> update(@Header("Authorization") String authToken,
                                 @Field("id") String id,
                                 @Field("name") String name,
                                 @Field("phone_number") String no_hp,
                                 @Field("address") String alamat
                                 );

    
}
