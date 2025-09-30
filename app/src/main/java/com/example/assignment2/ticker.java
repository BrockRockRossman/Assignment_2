package com.example.assignment2;

public class ticker
{
    private String tickerName;
    private boolean selected;



    public ticker(String tickerName) {
        this.tickerName = tickerName;
    }

    public ticker(String tickerName, boolean selected)
    {
        this.tickerName = tickerName;
        this.selected = selected;
    }


    public String getTickerName() {
        return tickerName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return tickerName;
    }
}
