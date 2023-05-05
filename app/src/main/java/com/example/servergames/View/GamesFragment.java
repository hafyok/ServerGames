package com.example.servergames.View;


import static java.util.stream.Collectors.toList;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.servergames.Model.Game;
import com.example.servergames.View.Adapters.GamesAdapter;
import com.example.servergames.ViewModel.GamesViewModel;
import com.example.servergames.databinding.FragmentGamesBinding;

public class GamesFragment extends Fragment {
    private GamesViewModel mViewModel;
    private FragmentGamesBinding binding;
    //private Person.Role role;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGamesBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(GamesViewModel.class);

        View v = binding.getRoot();
        mViewModel.getAllGames().observe(getViewLifecycleOwner(), games -> {
            binding.peopleRecyclerView.setAdapter(
                    new GamesAdapter(
                            this,
                            games.stream().map(Game::getName).collect(toList()),
                            games.stream().map(Game::getBackground_img).collect(toList())
                    )
            );
        });

        binding.peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
}