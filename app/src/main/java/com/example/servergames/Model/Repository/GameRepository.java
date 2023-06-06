package com.example.servergames.Model.Repository;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.servergames.BuildConfig;
import com.example.servergames.Model.Game;
import com.example.servergames.Model.POJO.PaginatedGamesPOJO;
import Network.RAWGapi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameRepository {

    private RAWGapi api;
    private Retrofit retrofit;
    private static GameRepository mInstance;

    MutableLiveData<List<Game>> list = new MutableLiveData<>();
    private Gson gson = new GsonBuilder().create();

    public GameRepository(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RAWGapi.class);
    }

    public  LiveData<List<Game>> getAllGames(){
        MutableLiveData<List<Game>> result = new MutableLiveData<>();

        api.getListOfGames(BuildConfig.RAWG_API_KEY).enqueue(new Callback<PaginatedGamesPOJO>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<PaginatedGamesPOJO> call, Response<PaginatedGamesPOJO> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            result.setValue(response.body().getResults().stream().map(pojo -> new Game(pojo.getId(),
                                    pojo.getName(), pojo.getBackground_img())).collect(Collectors.toList()));
                        }
                    }
                }else {
                    if (response.errorBody() != null) {
                        try {
                            Log.d("GameRepository_ServerGames", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PaginatedGamesPOJO> call, Throwable t) {
                Log.d("Successful", "NO");
            }
        });
        return result;
    }

    public static GameRepository getInstance(){
        if (mInstance == null){
            mInstance = new GameRepository();
        }
        return mInstance;
    }
}
