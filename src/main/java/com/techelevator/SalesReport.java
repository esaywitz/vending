package com.techelevator;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {
    Map<String, Integer>  report;
    double currentSales;

    public SalesReport(){
        report = new HashMap<>();
    }

    public void addItem(String name, int quanity){
        report.put(name, quanity);
    }

    public void updateQuanity(String name){
        report.put(name,report.get(name) + 1);
    }

    public void displaySales(PrintWriter pw){
        for (Map.Entry<String,Integer> item: report.entrySet()){
            pw.println(item.getKey() + "|" + item.getValue());
        }
        pw.println(currentSales);
        pw.flush();

    }

    public void displayOnConsole(){
        for (Map.Entry<String,Integer> item: report.entrySet()){
            System.out.println(item.getKey() + "|" + item.getValue());
        }
        System.out.println("TOTAL SALES: $" + currentSales);

    }

    public void setCurrentSales(double currentSales) {
        this.currentSales = currentSales;
    }

    public void updateCurrentSales(double price){
        currentSales += price;
    }
}
