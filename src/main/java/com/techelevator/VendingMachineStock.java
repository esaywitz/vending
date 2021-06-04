package com.techelevator;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class VendingMachineStock {

    private Map<Item, Integer> stock;




    public VendingMachineStock(){
        stock = new LinkedHashMap<>();
    }

    public int getNumberItems(){
        return stock.size();
    }

    public Map<Item, Integer> getStock(){
        return stock;
    }
    public void addAnItem(Item item){
        stock.put(item, 5);
    }

    public void changeStock(String position){
        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            if (entry.getKey().getPosition().equals(position.toUpperCase())){
                stock.put(entry.getKey(), entry.getValue()-1);

            }
        }

    }
    public Item getItem(String position){

        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            if (entry.getKey().getPosition().equals(position.toUpperCase())){
                return entry.getKey();

            }
        }
        //this should never occur
        return null;
    }
    public int getQuantity(String position){

        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            if (entry.getKey().getPosition().equals(position.toUpperCase())){
                return entry.getValue();

            }
        }
        // This return should never occur
        return -1;
    }
    public boolean doesPositionExist(String position){

        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            if (entry.getKey().getPosition().equals(position.toUpperCase())){
                return true;

            }
        }
        return false;
    }

    public void displayAllItem(){
        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
           String position = entry.getKey().getPosition();
           String name = entry.getKey().getName();
           double price = entry.getKey().getPrice();
           String type = entry.getKey().getType();
           int quantity = entry.getValue();
           if (quantity != 0) {
               System.out.println(position + " " + name + " $" + price + " " + type + " " + quantity);
           }else{
               System.out.println(position + " " + name + " $" + price + " " + type + " " + "SOLD OUT");
           }
        }
    }
    // method used for testing purposes
    public String displayAllItems(){
        String result = "";
        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            String position = entry.getKey().getPosition();
            String name = entry.getKey().getName();
            double price = entry.getKey().getPrice();
            String type = entry.getKey().getType();
            int quantity = entry.getValue();
            if (quantity != 0) {
                result += position + " " + name + " $" + price + " " + type + " " + quantity + "\n";
            }else{
                result += position + " " + name + " $" + price + " " + type + " " + "SOLD OUT" + "\n";
            }
        }
        return result;
    }

    public void reportWrite(PrintWriter pw){
        for (Map.Entry<Item, Integer> entry : stock.entrySet()){
            pw.println(entry.getKey().getName() + "|" + "0");
        }
        pw.println(0);
        pw.flush();
    }

}
