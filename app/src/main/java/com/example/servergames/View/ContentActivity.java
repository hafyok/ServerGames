package com.example.servergames.View;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.servergames.R;
import com.example.servergames.ViewModel.ContentActivityViewModel;
import com.example.servergames.databinding.ContentLayoutBinding;

public class ContentActivity extends AppCompatActivity {
    private ContentActivityViewModel mViewModel;
    private ContentLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        binding.tvTitle.setText("TITLE");
        binding.tvContent.setText("CONTENT");
        binding.imageContent.setImageResource(getIntent().getIntExtra(
                "image", R.drawable.ic_baseline_error_24));
    }

    /*@Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ContentLayoutBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this).get(ContentActivityViewModel.class);

        View view = binding.getRoot();



        return view;
    }*/
}
