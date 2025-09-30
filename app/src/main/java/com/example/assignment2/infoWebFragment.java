package com.example.assignment2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedList;


public class infoWebFragment extends Fragment {

    private myViewModel webViewModel;

    public infoWebFragment() {
        // Required empty public constructor
    }

    WebView webview;

    ArrayList<ticker> tickerList;



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
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);




         webview = (WebView) view.findViewById(R.id.infoWeb);


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    String url) {
                return false;
            }
        });
        webview.loadUrl("https://seekingalpha.com/");




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webViewModel = new ViewModelProvider(getActivity()).get(myViewModel.class);


        webViewModel.gettickers().observe(getViewLifecycleOwner(), new Observer<ArrayList<ticker>>() {
            @Override
            public void onChanged(ArrayList<ticker> tickers) {
                tickerList = tickers;

                for(int i = 0; i < tickerList.size(); i++)
                {
                    ticker tick = tickerList.get(i);
                    if(tick.isSelected())
                    {
                        webview.loadUrl("https://seekingalpha.com/symbol/" + tick.getTickerName());
                    }
                }

                webview.loadUrl("https://seekingalpha.com/symbol/NEE" );

            }
        });
    }




}