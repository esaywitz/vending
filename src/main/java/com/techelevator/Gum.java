package com.techelevator;

public class Gum extends Item{
    private String type = "gum";

    public Gum(String position, String name, double price){
        super(position, name, price);

    }
    public String getEatingMessage(){

        return "Chew Chew, Yum!";
    }
    public String getType(){
        return type;
    }
}
