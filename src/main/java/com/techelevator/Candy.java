package com.techelevator;

public class Candy extends Item{

    private String type = "Candy";

    public Candy(String position, String name, double price){
        super(position, name, price);

    }
    public String getEatingMessage(){

        return "Munch Munch, Yum!";
    }
    public String getType(){
        return type;
    }
}
