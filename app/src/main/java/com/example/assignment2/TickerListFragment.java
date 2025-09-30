package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;


public class TickerListFragment extends Fragment {

    ListView tickerList;
    ArrayList<ticker> tickerArr = new ArrayList<>();
    String [] tickerSArr = {"NEE", "AAPL", "DIS"};

    private myViewModel tickerViewModel;


    public TickerListFragment() {

    }

    AdapterView.OnItemClickListener selectListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            ticker selected = (ticker) parent.getItemAtPosition(position);

            boolean chosen = selected.isSelected();
            String name = selected.getTickerName();



            for(int i = 0; i < tickerArr.size(); i++)
            {
                if (selected.toString().equals(tickerArr.get(i).toString())) {

                    Log.i("test true", "true " + i);
                    tickerArr.get(i).setSelected(true);
                    tickerViewModel.tickers.setValue(tickerArr);

                }
                else {
                    Log.i("test false", "false " + i);
                    tickerArr.get(i).setSelected(false);
                }
            }



        };

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

        tickerArr = new ArrayList<>();
        tickerArr.add(new ticker("NEE", false));
        tickerArr.add(new ticker("AAPL", false));
        tickerArr.add(new ticker("DIS", false));



        ArrayAdapter<ticker> adapter = new ArrayAdapter<ticker>(context,
                android.R.layout.simple_list_item_1, tickerArr);
        tickerList.setAdapter(adapter);

        tickerList.setOnItemClickListener(selectListener);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tickerViewModel = new ViewModelProvider(getActivity()).get(myViewModel.class);

        tickerViewModel.gettickers().observe(getViewLifecycleOwner(), new Observer<ArrayList<ticker>>() {
            @Override
            public void onChanged(ArrayList<ticker> tickers) {


                ArrayAdapter<ticker> adapter = new ArrayAdapter<ticker>(getActivity(),
                        android.R.layout.simple_list_item_1, tickers);
                tickerList.setAdapter(adapter);

            }
        });


    }
}