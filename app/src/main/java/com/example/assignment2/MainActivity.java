package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    public myViewModel sharedModel;
    smsReciever reciever;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        sharedModel = new ViewModelProvider(this).get(myViewModel.class);

        // Broadcast Reciever registration
        reciever = new smsReciever();
        IntentFilter filter = new
                IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(reciever, filter);

        if (savedInstanceState == null) {

            // Setting up fragments
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();

            // tickerListFragment
            TickerListFragment tl = new TickerListFragment();
            trans.add(R.id.tickerList, tl, "tickerList");

            // infoWebFragment
            infoWebFragment iw = new infoWebFragment();
            trans.add(R.id.infoWeb, iw, "infoweb");

            trans.commit();
        }

    }

    public myViewModel getSharedModel(){
        return sharedModel;
    }
}