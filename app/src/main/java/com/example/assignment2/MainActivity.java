package com.example.assignment2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();

            TickerListFragment tl = new TickerListFragment();
            trans.add(R.id.tickerList, tl, "tickerList");

            infoWebFragment iw = new infoWebFragment();
            trans.add(R.id.infoWeb, iw, "infoweb");

            trans.commit();
        }

    }
}