package com.example.servergames.View.Adapters.viewHolders;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servergames.databinding.GameElementBinding;

public class GameViewHolder extends RecyclerView.ViewHolder {
    RadioButton radioButton;
    ImageView mImageView;
    TextView textView;

    public GameViewHolder(GameElementBinding ceb) {
        super(ceb.getRoot());
        radioButton = ceb.rButton2;
        mImageView = ceb.gameImageView;
        textView = ceb.describeGame;
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }
    public ImageView getImageView(){
        return mImageView;
    }
    public TextView getTextView(){
        return textView;
    }
}
