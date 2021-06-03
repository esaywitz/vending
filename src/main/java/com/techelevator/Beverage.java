package com.techelevator;

public class Beverage extends Item{

    private String type = "beverage";

    public Beverage(String position, String name, double price){
        super(position, name, price);

    }
    public String getEatingMessage(){

        return "Glug Glug, Yum!";
    }
    public String getType(){
        return type;
    }

}
