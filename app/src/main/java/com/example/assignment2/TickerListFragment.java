package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class TickerListFragment extends Fragment {

    ListView tickerList;
    ArrayList<ticker> tickerArr;
    String [] tickerSArr = {"NEE", "AAPL", "DIS"};


    public TickerListFragment() {

    }

    AdapterView.OnItemClickListener selectListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ticker selected = (ticker) parent.getItemAtPosition(position);
            Log.i("cheese", "cheese");
            Toast.makeText(getActivity().getApplicationContext(), "hi hi " + selected.getTickerName(), Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        Context context = getContext();
        tickerList = view.findViewById(R.id.tickerList);


        ArrayAdapter adapter = new ArrayAdapter(context,
                android.R.layout.simple_list_item_1, tickerSArr);
        tickerList.setAdapter(adapter);




        return view;
    }
}