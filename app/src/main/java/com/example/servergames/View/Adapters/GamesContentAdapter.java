package com.example.servergames.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.servergames.R;
import com.example.servergames.View.Adapters.viewHolders.GameContentViewHolder;
import com.example.servergames.View.ContentActivity;
import com.example.servergames.databinding.ContentLayoutBinding;

import java.util.List;

class GamesContentAdapter extends RecyclerView.Adapter<GameContentViewHolder> {
    private final String games;
    private final List<String> urlImg;
    ContentActivity contentActivity;
    private Context mContext;

    public GamesContentAdapter(String games, List<String> urlImg, ContentActivity contentActivity) {
        this.games = games;
        this.urlImg = urlImg;
        this.contentActivity = contentActivity;
        mContext = contentActivity.getApplicationContext();
    }

    @NonNull
    @Override
    public GameContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentLayoutBinding binding = ContentLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GameContentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameContentViewHolder holder, int position) {
        ImageView imageView = holder.getImageView();
        holder.getTitle().setText(games);
        //holder.getTextView().setText(urlImg.get(position));

        Glide.with(mContext)
                .load(urlImg.get(position))
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(imageView);

    }



    @Override
    public int getItemCount() {
    // Implementation for getItemCount
        return 1;
    }


        
}


