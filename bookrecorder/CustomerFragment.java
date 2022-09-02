package com.example.bookrecorder;

import static com.example.bookrecorder.R.*;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookrecorder.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CustomerFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listview;
    Button buttonCustomer;
    TextView txtName, txtDebit, txtCredit, txtBalance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(layout.fragment_customer, container, false);

       /* txtName = view.findViewById(id.edtname);
        txtDebit = view.findViewById(id.edtdebit);
        txtCredit = view.findViewById(id.edtcredit);
        txtBalance = view.findViewById(id.edtbalance);
        buttonCustomer = (Button) view.findViewById(id.buttonCustomer);

        // partiesDb = database.getInstance().getReference().child("Parties");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partiesDb = database.getReference();

// Attach a listener to read the data at our posts reference
        partiesDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot partiesDataSnap:dataSnapshot.getChildren()){
                    Parties partiesInfo = dataSnapshot.getValue(Parties.class);
                    System.out.println(partiesInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });*/
        listview=view.findViewById(R.id.listview);
        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(getActivity(), layout.parties_list,list );
        listview.setAdapter(adapter);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Parties");
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

        buttonCustomer = (Button) view.findViewById(R.id.buttonCustomer);
        buttonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(id.fragment_Frame, new addNewCustomer()).commit();
            }
        });
        return view;
    }

}
