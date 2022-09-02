package com.example.bookrecorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class addNewInventory extends Fragment {

    EditText itemInventory;
    EditText qtyInventory;
    Spinner unit4;
    EditText rateInventory;
    Button donebtn4;


    DatabaseReference inventoryDB;
    long maxid = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_new_inventory, container, false);

        itemInventory = view.findViewById(R.id.iteminventory);
        qtyInventory = view.findViewById(R.id.qtyinventory);
        unit4 = view.findViewById(R.id.unit4);
        rateInventory = view.findViewById(R.id.rateinventory);

        String[] unit={"Kg","Paket","Liter"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unit);
        unit4.setAdapter(adapter);


        donebtn4 = (Button) view.findViewById(R.id.donebtn4);

        inventoryDB = FirebaseDatabase.getInstance().getReference().child("Inventory");
        inventoryDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxid = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        donebtn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertInventoryData();


            }
        });
        return view;
    }


    private void insertInventoryData() {
        String item = itemInventory.getText().toString().trim();
        String quntity = qtyInventory.getText().toString().trim();
        String unit = unit4.toString().trim();
        String rate = rateInventory.getText().toString().trim();

        if (TextUtils.isEmpty(item)) {
            Toast.makeText(getActivity(), "Please enter the item item", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(quntity)) {
            Toast.makeText(getActivity(), "Please enter the quntity", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rate)) {
            Toast.makeText(getActivity(), "Please enter the rate", Toast.LENGTH_SHORT).show();
            return;
        }
        Inventory inventory = new Inventory(item, quntity, unit, rate);
        inventoryDB.child(String.valueOf(maxid+1)).setValue(inventory);
        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new Inventory_Fragment()).commit();

    }
}





