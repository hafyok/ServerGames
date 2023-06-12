package com.example.servergames.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.servergames.Model.POJO.CRUDUser;
import com.example.servergames.R;
import com.example.servergames.UserDataHolder;
import com.example.servergames.ViewModel.ContentActivityViewModel;
import com.example.servergames.databinding.ContentLayoutBinding;

import Network.ApiForFirebase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentActivity extends AppCompatActivity {
    private ContentActivityViewModel mViewModel;
    private ContentLayoutBinding binding;
    private ApiForFirebase api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ContentLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CRUDUser currentUser = UserDataHolder.getInstance().getCurrentUser();
        // Используйте экземпляр currentUser для нужных вам операций
        Toast.makeText(this, currentUser.getName().toString(), Toast.LENGTH_SHORT).show();

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String url = getIntent().getStringExtra("image");
        String id = getIntent().getStringExtra(("id"));
        //Toast.makeText(this, url, Toast.LENGTH_LONG).show();

        ImageView image = binding.imageContent;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(image);

        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
        binding.idTv.setText(id);

        String email = currentUser.getName();
        CheckBox checkBox = binding.checkBox;
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Код, который нужно выполнить при отметке CheckBox
                Toast.makeText(ContentActivity.this, "CheckBox отмечен", Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.56.1:8080/") //здесь IP-адрес моего компа
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Создаём экземпляр пользователя, чтобы затем отправить в бд
                /*CRUDUser user = new CRUDUser();
                user.setName(currentUser.getName());
                user.setDocumentId(currentUser.getName());*/
                api = retrofit.create(ApiForFirebase.class);

                int num = Integer.valueOf(id);
                Call<CRUDUser> call = api.addRecordId(email, Integer.parseInt(id));//Передаём email и id игры, которую передаём
                Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
                Log.d("INTEGER", id);

                call.enqueue(new Callback<CRUDUser>() {
                    @Override
                    public void onResponse(Call<CRUDUser> call, Response<CRUDUser> response) {
                        if (response.isSuccessful()) {
                            CRUDUser createdUser = response.body();
                            Log.d("POST user", "Response: " + createdUser.toString());
                        } else {
                            Log.d("POST user", "Error onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<CRUDUser> call, Throwable t) {
                        Log.d("POST user", "Error onFailure: " + t.getMessage());
                    }
                });
            } else {
                // Код, который нужно выполнить при снятии отметки с CheckBox
                Toast.makeText(ContentActivity.this, "CheckBox снят", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
