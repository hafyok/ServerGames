package com.example.servergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.widget.Toast;

import com.example.servergames.Model.POJO.CRUDUser;
import com.example.servergames.View.GamesFragment;
import com.example.servergames.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;

import Network.ApiForFirebase;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);


        FirebaseApp app = FirebaseApp.getInstance();
        if (app != null) {
            Log.d("Firebase", "Firebase app is initialized");
        } else {
            Log.d("Firebase", "Firebase app is not initialized");
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.Games:
                    replaceFragment(new GamesFragment());
                    break;
            /*case R.id.computer:
                replaceFragment(new ComputersFragment());
                break;*/

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }
}