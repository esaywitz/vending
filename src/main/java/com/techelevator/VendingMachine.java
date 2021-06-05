package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("vendingmachine.csv");
        Scanner scanner = new Scanner(inputFile);
        VendingMachineStock stock = new VendingMachineStock();
        reStock(scanner, stock);

        Scanner customerInput = new Scanner(System.in);
        FileWriter logFile = new FileWriter("log.txt", true);
        PrintWriter write = new PrintWriter(logFile);
        SalesReport salesReport = new SalesReport();
        File newFile = new File("SalesReport.txt");
        Scanner saleScanner = new Scanner(newFile);

        try {
            generateSalesReport(salesReport, saleScanner);
        } catch (Exception e){
            // This will only occur if saleReport.txt is empty
            // If it is empty then the map will be built using the
            // base case, though if salesReport.txt is empty, it would mean that the data was wiped
            // so the current sales would be reset to zero
            FileWriter saleFile = new FileWriter("SalesReport.txt");
            PrintWriter saleWrite = new PrintWriter(saleFile);
            stock.reportWrite(saleWrite);
            generateSalesReport(salesReport, saleScanner);
        }

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("MM/dd/yyyy HH:mm:ss a");

        int choice = 0;
        while(choice != 3) {
            displayMenu();
            choice = parseUserInput(customerInput);
            if (choice == 1) {
                stock.displayAllItem();
            } else if (choice == 2) {
                choice = purchase(stock, customerInput, write, salesReport, sdf);
            } else if (choice == 4){
                printSalesReport(salesReport);
            }
        }

        write.flush();
        FileWriter saleFile = new FileWriter("SalesReport.txt");
        PrintWriter saleWrite = new PrintWriter(saleFile);
        salesReport.displaySales(saleWrite);
    }

    private static void printSalesReport(SalesReport salesReport) throws IOException {
        FileWriter saleFile = new FileWriter("SalesReport"+ new Date() +".txt");
        PrintWriter saleWrite = new PrintWriter(saleFile);
        salesReport.displaySales(saleWrite);
    }

    public static void reStock(Scanner scanner, VendingMachineStock stock) {
        while (scanner.hasNextLine()){
            String inputLine = scanner.nextLine();
            String[] items = inputLine.split("\\|");

            if (items[3].equals("Chip")) {
                Chip chip = new Chip(items[0],items[1],Double.parseDouble(items[2]));
                stock.addAnItem(chip);
            } else if (items[3].equals("Candy")) {
                Candy candy = new Candy(items[0],items[1],Double.parseDouble(items[2]));
                stock.addAnItem(candy);
            } else if (items[3].equals("Drink")) {
                Beverage beverage = new Beverage(items[0],items[1],Double.parseDouble(items[2]));
                stock.addAnItem(beverage);
            } else {
                Gum gum = new Gum(items[0],items[1],Double.parseDouble(items[2]));
                stock.addAnItem(gum);
            }
        }
    }

    public static void generateSalesReport(SalesReport sp, Scanner saleScanner) {
        int count = 1;
        while (count < 17 && saleScanner.hasNextLine()) {
            count++;
            String line = saleScanner.nextLine();
            String[] arrayLine = line.split("\\|");
            sp.addItem(arrayLine[0], Integer.valueOf(arrayLine[1]));
        }
        String line = saleScanner.nextLine();
        sp.setCurrentSales(Double.valueOf(line));
    }

    public static void displayMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
    }

    public static int parseUserInput(Scanner customerInput) {
        int choice;
        try {
            choice = Integer.parseInt(customerInput.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a 1, 2, or, 3 ");
            choice = 0;
        }
        return choice;
    }

    public static int purchase(VendingMachineStock stock, Scanner customerInput, PrintWriter write, SalesReport sp, SimpleDateFormat sdf) {
        int choice;
        PurchaseItem purchase = new PurchaseItem();

        while (true) {
            displayPurchaseMenu(purchase);
            choice = parseUserInput(customerInput);

            if (choice == 1) {
                feedMoney(customerInput, write, sdf, purchase);
            } else if (choice == 2) {
                selectProduct(stock, customerInput, write, sp, sdf, purchase);
            } else if (choice == 3) {
                System.out.println("Are you sure you want to finish your transaction? Yes/No");
                String intend = customerInput.nextLine();

                if (intend.toLowerCase().equals("yes")) {
                    String output = purchase.finishTransaction();
                    write.println(sdf.format(new Date()) + " " + output);
                    choice = 0;
                    break;
                }
            }
        }
        return choice;
    }

    public static void displayPurchaseMenu(PurchaseItem purchase) {
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("Current Money Provided: $" + purchase.getBalance());
    }

    public static void selectProduct(VendingMachineStock stock, Scanner customerInput, PrintWriter write, SalesReport sp, SimpleDateFormat sdf, PurchaseItem purchase) {
        stock.displayAllItem();
        System.out.println("Please choose an item by its position:");
        String position = customerInput.nextLine();
        System.out.println("Did you mean to type " + position + "? Enter Yes/No");
        String intend = customerInput.nextLine();
        if (intend.toLowerCase().equals("yes")) {
            if (stock.doesPositionExist(position)) {
                int quantity = stock.getQuantity(position);
                if (quantity != 0) {
                    Item item = stock.getItem(position);
                    if (purchase.getBalance() >= item.getPrice()){
                        purchaseItem(stock, write, sp, sdf, purchase, position, item);
                    } else {
                        System.out.println("Not enough money to buy this item");
                    }
                } else {
                    System.out.println("Sold out of the item");
                }
            } else {
                System.out.println("This position does not exist");
            }
        }
    }

    public static void purchaseItem(VendingMachineStock stock, PrintWriter write, SalesReport sp, SimpleDateFormat sdf, PurchaseItem purchase, String position, Item item) {
        String productOutput = purchase.selectAProduct(item);
        sp.updateQuanity(item.getName());
        sp.updateCurrentSales(item.getPrice());
        write.println(sdf.format(new Date()) + " " + productOutput);
        stock.changeStock(position);
    }

    public static void feedMoney(Scanner customerInput, PrintWriter write, SimpleDateFormat sdf, PurchaseItem purchase) {
        System.out.println("Please enter your whole dollars:");
        try {
            String input = customerInput.nextLine();
            double inputMoney = Double.parseDouble(input);
            String output = purchase.enterMoney(inputMoney);
            if (output.equals("This is not a whole dollar")){
                System.out.println("This is not a whole dollar");
            } else {
                write.println(sdf.format(new Date()) + " " + output);
            }
        } catch (NumberFormatException e){
            System.out.println("That is not a whole number");
        }
    }
}
