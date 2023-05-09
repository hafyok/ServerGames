package com.example.servergames.View.Adapters.viewHolders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servergames.View.ContentActivity;
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

    public void bind(String title, String content, String url, ImageView imageView, Context context) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("image", url);
                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
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
