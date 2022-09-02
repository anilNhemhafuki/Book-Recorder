package com.example.bookrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookrecorder.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homePage extends AppCompatActivity {

    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.customer) {
                    loadFragment(new CustomerFragment(), false);
                } else if (id == R.id.sales) {
                    loadFragment(new SalesFragment(), false);

                } else if (id == R.id.production) {
                    loadFragment(new ProductionFragment(), false);

                } else if (id == R.id.inventory) {
                    loadFragment(new Inventory_Fragment(), false);
                }
                else {
                    loadFragment(new MainFragment(), true);
                }
            }
        });
    }

    public void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag) {
            ft.add(R.id.fragment_Frame, new MainFragment());
        } else {
            ft.replace(R.id.fragment_Frame, fragment);
        }
        ft.commit();
    }
}
