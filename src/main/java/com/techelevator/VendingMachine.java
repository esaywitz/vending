package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("vendingmachine.csv");
        Scanner scanner = new Scanner(inputFile);
        VendingMachineStock stock = new VendingMachineStock();
        reStock(scanner, stock);
        Scanner customerInput = new Scanner(System.in);

        FileWriter logFile = new FileWriter("log.txt",true);
        PrintWriter write = new PrintWriter(logFile);



        // FileWriter saleFile = new FileWriter("SalesReport.txt");
        // PrintWriter saleWrite = new PrintWriter(saleFile);
        // stock.reportWrite(saleWrite);

        File newFile = new File("SalesReport.txt");
        Scanner saleScanner = new Scanner(newFile);
        SalesReport sp = new SalesReport();
        int count=1;
        while(count < 17 && saleScanner.hasNextLine()) {
            count++;
            String line = saleScanner.nextLine();
            String[] arrayLine = line.split("\\|");
            sp.addItem(arrayLine[0], Integer.valueOf(arrayLine[1]));
        }
        String line = saleScanner.nextLine();
        sp.setCurrentSales(Double.valueOf(line));

        FileWriter saleFile = new FileWriter("SalesReport.txt");
        PrintWriter saleWrite = new PrintWriter(saleFile);


        int choice = 0;
        while(choice != 3) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            choice = Integer.parseInt(customerInput.nextLine());

            if (choice == 1){
                stock.displayAllItem();
            }else if (choice == 2){
                PurchaseItem purchase = new PurchaseItem();
                while(true) {
                    System.out.println("(1) Feed Money");
                    System.out.println("(2) Select Product");
                    System.out.println("(3) Finish Transaction");
                    System.out.println("Current Money Provided: $" + purchase.getBalance());
                    choice = Integer.parseInt(customerInput.nextLine());

                    if (choice == 1){
                        System.out.println("Please enter your whole dollars:");
                        double inputMoney = Double.parseDouble(customerInput.nextLine());
                        String output = purchase.enterMoney(inputMoney);
                        if (output.equals("This is not a whole dollar")){
                            System.out.println("This is not a whole dollar");
                        }else {
                            write.println(output);
                        }
                    }else if (choice == 2){
                        stock.displayAllItem();
                        System.out.println("Please choose an item by its position:");
                        String position = customerInput.nextLine();
                        System.out.println("Did you mean to type " + position + "? Enter Yes/No");
                        String intend = customerInput.nextLine();
                        if (intend.toLowerCase().equals("yes")){
                            if (stock.doesPositionExist(position)){
                                int quantity = stock.getQuantity(position);
                                if (quantity != 0){
                                    Item item = stock.getItem(position);
                                    if (purchase.getBalance() >= item.getPrice()){
                                        String productOutput = purchase.selectAProduct(item);
                                        sp.updateQuanity(item.getName());
                                        sp.updateCurrentSales(item.getPrice());
                                        write.println(productOutput);
                                        stock.changeStock(position);
                                    }else{
                                        System.out.println("Not enough money to buy this item");
                                    }
                                }else{
                                    System.out.println("Sold out of the item");
                                }
                            }else{
                                System.out.println("This position does not exist");
                            }
                        }else{

                        }

                    }else if (choice == 3){
                        System.out.println("Are you sure you want to finish your transaction? Yes/No");
                        String intend = customerInput.nextLine();
                        if (intend.toLowerCase().equals("yes")){
                            String output = purchase.finishTransaction();
                            write.println(output);

                            choice = 0;
                            break;

                        }else{

                        }


                    }else{

                    }

                }
            }else if (choice != 3){

            }else{

            }

        }
        write.flush();
        sp.displaySales(saleWrite);
        sp.displayOnConsole();


    }


    private static void reStock(Scanner scanner, VendingMachineStock stock) {
        while(scanner.hasNextLine()){
            String inputLine = scanner.nextLine();

            String[] items = inputLine.split("\\|");
           if (items[3].equals("Chip")){
               Chip chip = new Chip(items[0],items[1],Double.parseDouble(items[2]));
               stock.addAnItem(chip);
           }else if (items[3].equals("Candy")){
               Candy candy = new Candy(items[0],items[1],Double.parseDouble(items[2]));
               stock.addAnItem(candy);
           }else if (items[3].equals("Drink")){
               Beverage beverage = new Beverage(items[0],items[1],Double.parseDouble(items[2]));
               stock.addAnItem(beverage);
           }else{
               Gum gum = new Gum(items[0],items[1],Double.parseDouble(items[2]));
               stock.addAnItem(gum);
           }
        }
    }
}
