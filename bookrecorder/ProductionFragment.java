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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProductionFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listview;
    Button buttonProduction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_production, container, false);


        listview=view.findViewById(R.id.listview);
        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(getActivity(), R.layout.production_list,list );
        listview.setAdapter(adapter);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Production");
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
        buttonProduction = (Button) view.findViewById(R.id.buttonProduction);

        buttonProduction.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Frame, new addNewProduction()).commit();
            }
        });
        return view;
    }

}