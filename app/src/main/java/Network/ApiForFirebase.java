package Network;

import com.example.servergames.Model.POJO.CRUDUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiForFirebase {
    @GET("get")
    Call<CRUDUser> getUser(@Query("documentId") String documentId);

    @POST("create")
    Call<CRUDUser> createUser(@Body CRUDUser user);
}
