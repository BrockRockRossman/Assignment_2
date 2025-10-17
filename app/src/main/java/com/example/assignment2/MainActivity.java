package com.example.assignment2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    public myViewModel sharedModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // adding our viewmodel
        sharedModel = new ViewModelProvider(this).get(myViewModel.class);

        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            String[] perm = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this,perm,67);
        }

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

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        Toast.makeText(this, "activ: " + message, Toast.LENGTH_SHORT).show();



        // Checking if SMS is the right code
        if(message.matches("^<<[A-Za-z]+>>$"))
        {
            message = message.replace("<<", "");
            message = message.replace(">>", "");

            message = message.toUpperCase();

            Toast.makeText(this, "Valid code", Toast.LENGTH_SHORT).show();

            ticker tick = new ticker(message, false);

            sharedModel.addTicker(tick);
        }
        else
        {
            Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show();
        }



    }

    // This method is necessary
    public myViewModel getSharedModel(){
        return sharedModel;
    }
}