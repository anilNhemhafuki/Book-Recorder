package com.example.bookrecorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SalesFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
ListView listview;
    Button buttonSales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_sales, container, false);

        listview=view.findViewById(R.id.listview);

ArrayList<String> list=new ArrayList<>();
ArrayAdapter adapter=new ArrayAdapter<String>(getActivity(), R.layout.sales_list,list );
listview.setAdapter(adapter);
DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Sales");
reference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        list.clear();
        for(DataSnapshot snapshotData :snapshot.getChildren()){
            list.add(snapshotData.getValue().toString());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});


        buttonSales = (Button) view.findViewById(R.id.buttonSales);
        buttonSales.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new addNewSales()).commit();
            }
        });
        return view;
    }


}