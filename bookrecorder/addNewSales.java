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


public class addNewSales extends Fragment {


    EditText itemSales;
    EditText qtySales;
    Spinner unit2;
    EditText rateSales;
    Button donebtn2;


    DatabaseReference salesDB;
    long maxid = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_sales, container, false);

        itemSales = view.findViewById(R.id.itemsales);
        qtySales = view.findViewById(R.id.qtysales);
        unit2 = view.findViewById(R.id.unit2);
        rateSales = view.findViewById(R.id.ratesales);


        donebtn2 = (Button) view.findViewById(R.id.donebtn2);

        salesDB = FirebaseDatabase.getInstance().getReference().child("Sales");
        salesDB.addValueEventListener(new ValueEventListener() {
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

        donebtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertSalesData();


            }
        });
        return view;
    }


    private void insertSalesData() {
        String item = itemSales.getText().toString().trim();
        String quntity = qtySales.getText().toString().trim();
        String unit = unit2.toString().trim();
        String rate = rateSales.getText().toString().trim();

        if (TextUtils.isEmpty(item)) {
            Toast.makeText(getActivity(), "Please enter the item item", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(quntity)) {
            Toast.makeText(getActivity(), "Please enter the quntity", Toast.LENGTH_SHORT).show();
            return;
        }
        Sales sales = new Sales(item, quntity, unit, rate);
        salesDB.child(String.valueOf(maxid + 1)).setValue(sales);
        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new SalesFragment()).commit();

    }
}



