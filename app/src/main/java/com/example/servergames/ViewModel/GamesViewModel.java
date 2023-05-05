package com.example.servergames.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.servergames.Model.Game;
import com.example.servergames.Model.Repository.GameRepository;

import java.util.List;

public class GamesViewModel extends ViewModel {
    private GameRepository mRepository;

    public LiveData<List<Game>> getAllGames(){
        return GameRepository.getInstance().getAllGames();
    }
}
