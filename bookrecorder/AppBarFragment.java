package com.example.bookrecorder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppBarFragment extends Fragment {


 Button btnShare;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_app_bar, container, false);

        btnShare=view.findViewById(R.id.share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("");
                i.putExtra(Intent.EXTRA_SUBJECT,"Insert subject here");
                String app_url="Book recorder files";
                i.putExtra(Intent.EXTRA_SUBJECT, app_url);
                getActivity().startActivity(Intent.createChooser(i,"Share via"));
            }
        });
        return view;
    }


}