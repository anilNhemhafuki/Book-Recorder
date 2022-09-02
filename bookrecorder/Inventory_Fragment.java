package com.example.bookrecorder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Inventory_Fragment extends Fragment{

    Button buttonInventory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_inventory_, container, false);

        buttonInventory = (Button) v.findViewById(R.id.buttonInventory);
        buttonInventory.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new addNewInventory()).commit();
            }
        });
        return v;
    }

}