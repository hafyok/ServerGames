package Network;

import com.example.servergames.Model.POJO.PaginatedGamesPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RAWGapi {
    @GET("games")
    Call<PaginatedGamesPOJO> getListOfGames(@Query("key") String apiKey);
}
