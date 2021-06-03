package com.techelevator;

public class Item {
    private String position;
    private String name;
    private double price;

    public Item(String position, String name, double price){
        this.name = name;
        this.price = price;
        this.position = position;
    }

    public String getEatingMessage(){
        return null;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
