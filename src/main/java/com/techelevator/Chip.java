package com.techelevator;

public class Chip extends Item{

    private String type = "Chip";

    public Chip(String position, String name, double price){
        super(position, name, price);

    }
    public String getEatingMessage(){

        return "Crunch Crunch, Yum!";
    }
    public String getType(){
        return type;
    }
}
