package com.example.servergames.View;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.servergames.R;
import com.example.servergames.ViewModel.ContentActivityViewModel;
import com.example.servergames.databinding.ContentLayoutBinding;

public class ContentActivity extends AppCompatActivity {
    private ContentActivityViewModel mViewModel;
    private ContentLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ContentLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding.tvTitle.setText("TITLE");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String url = getIntent().getStringExtra("image");
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();

        ImageView image = binding.imageContent;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(image);

        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);

    }
}
