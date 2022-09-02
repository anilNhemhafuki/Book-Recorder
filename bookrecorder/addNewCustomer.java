package com.example.bookrecorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addNewCustomer extends Fragment {

    EditText edtName;
    EditText edtDebit;
    EditText edtCredit;
    EditText edtBalance;
    Button donebtn;


    DatabaseReference partiesDb;
    long maxid = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_customer, container, false);

        edtName = view.findViewById(R.id.edtname);
        edtDebit = view.findViewById(R.id.edtdebit);
        edtCredit = view.findViewById(R.id.edtcredit);
        edtBalance = view.findViewById(R.id.edtbalance);

        TextWatcher balanceAmt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtDebit.getText().toString().equals("") && !edtCredit.getText().toString().equals("")) {
                    int debit = Integer.parseInt(edtDebit.getText().toString());
                    int credit = Integer.parseInt(edtCredit.getText().toString());
                    edtBalance.setText(String.valueOf(debit - credit));

                }
                else if (edtDebit!=null) {
                    int debit = Integer.parseInt(edtDebit.getText().toString());
                    edtBalance.setText(String.valueOf(debit));
                }else {
                    /*int credit = Integer.parseInt(edtDebit.getText().toString());
                    edtBalance.setText(String.valueOf(credit));*/
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtDebit.addTextChangedListener(balanceAmt);
        edtCredit.addTextChangedListener(balanceAmt);

        donebtn = (Button) view.findViewById(R.id.donebtn);

        partiesDb = FirebaseDatabase.getInstance().getReference().child("Parties");
        partiesDb.addValueEventListener(new ValueEventListener() {
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

        donebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertPartiesData();


            }
        });
        return view;
    }

    private void insertPartiesData() {
        String name = edtName.getText().toString().trim();
        String debit = edtDebit.getText().toString().trim();
        String credit = edtCredit.getText().toString().trim();
        String balance = edtBalance.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "Please enter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(debit)) {
            Toast.makeText(getActivity(), "Please enter the debit amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(credit)) {
            Toast.makeText(getActivity(), "Please enter the credit amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(balance)) {
            Toast.makeText(getActivity(), "Please enter the balance amount", Toast.LENGTH_SHORT).show();
            return;
        }
        Parties parties = new Parties(name, debit, credit, balance);
        partiesDb.child(String.valueOf(maxid+1)).setValue(parties);
        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new CustomerFragment()).commit();

    }
}