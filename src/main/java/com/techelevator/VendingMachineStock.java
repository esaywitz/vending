package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineStock {

    private Map<Item, Integer> stock;

    public VendingMachineStock(){
        stock = new HashMap<>();
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

}
