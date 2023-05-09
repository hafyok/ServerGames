package com.example.servergames.View.Adapters.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servergames.databinding.ContentLayoutBinding;

public class GameContentViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView content;
    ImageView imageView;

    public GameContentViewHolder(ContentLayoutBinding binding) {
        super(binding.getRoot());
        title = binding.tvTitle;
        content = binding.tvContent;
        imageView = binding.imageContent;
    }

    public TextView getTitle(){
        return title;
    }
    public TextView getContent(){
        return content;
    }
    public ImageView getImageView(){
        return imageView;
    }
}
