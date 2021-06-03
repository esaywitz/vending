package com.techelevator;

public class PurchaseItem {
    private double balance;

    public PurchaseItem(){
        balance = 0;
    }

    public double getBalance(){
        return balance;
    }
    public String enterMoney(double money){
        if (money%1!=0)
            return "This is not a whole dollar";
        balance+=money;
        return "FEED MONEY: $" +  money + " $"  + balance;
    }
    public String selectAProduct(Item item, double quantity){


        double price = item.getPrice();
        double tempbalance = balance;
        balance-=price;
        System.out.println(item.getName() + " $" + price + " $" +  balance + " " + item.getEatingMessage());
        return item.getName() + " " + item.getPosition() + " $" + tempbalance + " $" + balance;

    }

    public String finishTransaction(){
        double remainingChange = balance;
        int quarters = (int) (balance/.25);
        remainingChange = ((balance/.25)%1)/4;
        int dimes = (int)(remainingChange/.1);
        remainingChange = ((balance/.25)%1)/10;
        int nickels = (int)(remainingChange/.05);


        System.out.println("your change is " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels");
        return "GIVE CHANGE: $" + balance + " $0.0";
    }
}
