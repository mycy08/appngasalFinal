package tercyduk.appngasal.apihelper;

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

    
}
