package com.example.servergames.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.servergames.R;
import com.example.servergames.View.GamesFragment;
import com.example.servergames.databinding.FragmentGamesBinding;
import com.example.servergames.databinding.GameElementBinding;

import java.util.List;

import com.example.servergames.View.Adapters.viewHolders.GameViewHolder;

public class GamesAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private final List<String> games;
    private final GamesFragment gamesFragment;
    private final List<String> urlImg;
    private final List<Integer> id;
    FragmentGamesBinding binding;
    private Context mContext;


    public GamesAdapter(GamesFragment gamesFragment, List<String> games, List<String> urlImg, List<Integer> id){
        this.gamesFragment = gamesFragment;
        this.games = games;
        this.urlImg = urlImg;
        this.id = id;
        mContext = gamesFragment.getContext();
    }


    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameElementBinding ceb = GameElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new GameViewHolder(ceb);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        holder.getRadioButton().setText(games.get(position));

        Glide.with(mContext)
                .load(urlImg.get(position))
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(imageView);

        holder.bind(games.get(position), "contentt", urlImg.get(position), imageView, id.get(position), mContext);
        /*Toast.makeText(mContext, games.get(position), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, id.get(position).toString(), Toast.LENGTH_SHORT).show();*/
    }


    @Override
    public int getItemCount() {
        return games.size();
    }
}
