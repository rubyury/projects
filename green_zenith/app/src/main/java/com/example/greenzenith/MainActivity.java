package com.example.greenzenith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.greenzenith.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int[] id = {R.id.navHome, R.id.navPlants,
            R.id.navNotifications, R.id.navUser};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new FirstScreen());
        binding.bottomNavApp.setBackground(null);

        binding.bottomNav.setOnItemSelectedListener(item -> {
            int idk = item.getItemId();
            if (idk == id[0]){
                replaceFragment(new Principal());
            } else if (idk == id[1]){
            } else if (idk == id[2]){
            } else if (idk == id[3]){
                replaceFragment(new User());
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void setMenuEnabled(boolean enabled) {
        binding.bottomNav.setVisibility(enabled ? View.VISIBLE : View.GONE);
    }
}