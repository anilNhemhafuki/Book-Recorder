package com.example.bookrecorder;

public class Production {
    String item;
    String quantity;
    String unit;
    String rate;

    public Production(String item, String quantity, String unit, String rate) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
        this.rate = rate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {

        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
}

