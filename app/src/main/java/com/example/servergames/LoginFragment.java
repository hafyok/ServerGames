package com.example.servergames;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.servergames.Model.POJO.CRUDUser;
import com.example.servergames.databinding.LoginLayoutBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Network.ApiForFirebase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends NavHostFragment {
    private LoginLayoutBinding binding;
    private FirebaseAuth mAuth;
    public ApiForFirebase api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {
            Toast.makeText(getContext(), "User not null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "User null", Toast.LENGTH_SHORT).show();
        }

        //////////////////////////////////////////////////////////////
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/") //здесь IP-адрес моего компа
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Получаю тестовые данные
        api = retrofit.create(ApiForFirebase.class);
        Call<CRUDUser> call = api.getUser("user_2");
        call.enqueue(new Callback<CRUDUser>() {
            @Override
            public void onResponse(Call<CRUDUser> call, Response<CRUDUser> response) {
                if (response.isSuccessful()) {
                    CRUDUser user = response.body();
                    // Обработка успешного ответа
                    Toast.makeText(getContext(), "Response: " + user.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("myAPI", "Response: " + user.toString());
                } else {
                    // Обработка ошибки
                    Toast.makeText(getContext(), "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("myAPI", "Error onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CRUDUser> call, Throwable t) {
                // Обработка ошибки
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("myAPI", "Error onFailure: " + t.getMessage());
            }
        });
        */
        //////////////////////////////////////////////////
    }


    private void signUp() {
        String email = binding.edEmail.getText().toString();
        String password = binding.edPassword.getText().toString();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "User SignUp Successful", Toast.LENGTH_SHORT).show();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.56.1:8080/") //здесь IP-адрес моего компа
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        //Создаём экземпляр пользователя, чтобы затем отправить в бд
                        CRUDUser user = new CRUDUser();
                        user.setName(email);
                        user.setDocumentId(email);

                        api = retrofit.create(ApiForFirebase.class);
                        Call<CRUDUser> call = api.createUser(user);
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
                        Toast.makeText(getContext(), "User SignUp failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void signIn() {
        String email = binding.edEmail.getText().toString();
        String password = binding.edPassword.getText().toString();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "User SignIn Successful", Toast.LENGTH_SHORT).show();
                        navigateToGamesFragment();
                    } else {
                        Toast.makeText(getContext(), "User SignIn failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void navigateToGamesFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.main_content);
        navController.navigate(R.id.fragment_games);
    }
}