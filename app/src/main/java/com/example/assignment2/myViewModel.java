package com.example.assignment2;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class myViewModel extends ViewModel {

    //  variables that store the data


    // The mutable live data is a type that is able to change between fragments and classes
    public MutableLiveData<ArrayList<ticker>> tickers;
    private ArrayList<ticker> tickerList;

    private int roundRobin = 1;


    // Method to retrieve data
    public LiveData<ArrayList<ticker>> gettickers() {
        if (tickers == null) {
            tickers = new MutableLiveData<ArrayList<ticker>>();
            loadtickers();
        }
        return tickers;
    }

    public void setTickers(MutableLiveData<ArrayList<ticker>> tickers) {

        this.tickers = tickers;
    }

    public MutableLiveData<ArrayList<ticker>> getTickers() {
        if(tickers == null){
            tickers = new MutableLiveData<>();
            ArrayList<ticker> tickerList = new ArrayList<>();
            tickerList.add(new ticker("NEE"));
            tickerList.add(new ticker("AAPL"));
            tickerList.add(new ticker("DIS"));
            tickers.setValue(tickerList);
        }
        return tickers;
    }

    public void setTicker(int i, ticker tick){
        ArrayList<ticker> list = tickers.getValue();
        list.set(i, tick);
        tickers.setValue(list);

    }

    // This allows us to add a new ticker to our list
    public void addTicker(ticker tick) {
        // Create a new list and populate with the current data from mutablelivedata
        Log.i("viewModel", "A");

        ArrayList<ticker> list = tickers.getValue();



        if(list.size() == 6)
        {
            list.remove(roundRobin);
            list.add(roundRobin, tick);

            roundRobin += 1;
            if(roundRobin > 5)
            {
                roundRobin = 0;
            }
        }

        Log.i("viewModel", tick.toString());


        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).setSelected(false);
        }

        Log.i("viewModel", "B");
        // add new item
        ticker tick2 = new ticker(tick.getTickerName(), true);
        list.add(tick2);



        Log.i("viewModel", "C");

        // Set the value of our mutable data to our list we just created
        tickers.setValue(list);

    }


    private void loadtickers() {
// Do an asynchronous operation to fetch tickers.
    }
}
