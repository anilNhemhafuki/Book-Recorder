package com.example.bookrecorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class addNewProduction extends Fragment {


    EditText itemProduction;
    EditText qtyProduction;
    Spinner unit3;
    EditText rateProduction;
    Button donebtn3;

    DatabaseReference productionDB;
    long maxid = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_new_production, container, false);

        itemProduction = view.findViewById(R.id.itemproduction);
        qtyProduction = view.findViewById(R.id.qtyproduction);
        unit3 = view.findViewById(R.id.unit3);
        rateProduction = view.findViewById(R.id.rateproduction);


        donebtn3 = (Button) view.findViewById(R.id.donebtn3);

        productionDB = FirebaseDatabase.getInstance().getReference().child("Production");
        productionDB.addValueEventListener(new ValueEventListener() {
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

        donebtn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertSalesData();


            }
        });
        return view;
    }


    private void insertSalesData() {
        String item = itemProduction.getText().toString().trim();
        String quntity = qtyProduction.getText().toString().trim();
        String unit = unit3.toString().trim();
        String rate = rateProduction.getText().toString().trim();

        if (TextUtils.isEmpty(item)) {
            Toast.makeText(getActivity(), "Please enter the item item", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(quntity)) {
            Toast.makeText(getActivity(), "Please enter the quntity", Toast.LENGTH_SHORT).show();
            return;
        }if (TextUtils.isEmpty(rate)) {
            Toast.makeText(getActivity(), "Please enter the rate", Toast.LENGTH_SHORT).show();
            return;
        }
        Production production = new Production(item, quntity, unit, rate);
        productionDB.child(String.valueOf(maxid + 1)).setValue(production);
        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new ProductionFragment()).commit();

    }
}



