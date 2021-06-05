package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private File file;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        file = new File("MainpurchaseMethodTest.txt");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void main_Test() throws IOException {
        String input = "1\n4\n3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        VendingMachine.main(null);

        Assert.assertEquals("(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n" +
                "A1 Potato Crisps $3.05 Chip 5\n" +
                "A2 Stackers $1.45 Chip 5\n" +
                "A3 Grain Waves $2.75 Chip 5\n" +
                "A4 Cloud Popcorn $3.65 Chip 5\n" +
                "B1 Moonpie $1.8 Candy 5\n" +
                "B2 Cowtales $1.5 Candy 5\n" +
                "B3 Wonka Bar $1.5 Candy 5\n" +
                "B4 Crunchie $1.75 Candy 5\n" +
                "C1 Cola $1.25 Drink 5\n" +
                "C2 Dr. Salt $1.5 Drink 5\n" +
                "C3 Mountain Melter $1.5 Drink 5\n" +
                "C4 Heavy $1.5 Drink 5\n" +
                "D1 U-Chews $0.85 Gum 5\n" +
                "D2 Little League Chew $0.95 Gum 5\n" +
                "D3 Chiclets $0.75 Gum 5\n" +
                "D4 Triplemint $0.75 Gum 5\n" +
                "(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n" +
                "(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n", outContent.toString());
    }

    @Test
    public void reStock_Test() throws FileNotFoundException {
        VendingMachineStock testVMStock = new VendingMachineStock();
        File textFile = new File("VendingMachinereStockTest.txt");
        Scanner textInput = new Scanner(textFile);
        VendingMachine.reStock(textInput, testVMStock);
        List<Item> testItem = new ArrayList<>();
        List<Integer> testQuantity = new ArrayList<>();
        for (Map.Entry<Item, Integer> item : testVMStock.getStock().entrySet()){
            testItem.add(item.getKey());
            testQuantity.add(item.getValue());
        }
        Chip expectChip = new Chip("A1","Potato Crisps",3.05);
        Candy expectCandy = new Candy("B1","Moonpie",1.80);
        Beverage expectBeverage = new Beverage("C1","Cola",1.25);
        Gum expectGum = new Gum("D1","U-Chews",0.85);

        Assert.assertEquals("A1",testItem.get(0).getPosition());
        Assert.assertEquals("Potato Crisps",testItem.get(0).getName());
        Assert.assertEquals(3.05,testItem.get(0).getPrice(),0.0);
        Assert.assertEquals("Chip",testItem.get(0).getType());
        Assert.assertEquals(5,(int)testQuantity.get(0));

        Assert.assertEquals("B1",testItem.get(1).getPosition());
        Assert.assertEquals("Moonpie",testItem.get(1).getName());
        Assert.assertEquals(1.80,testItem.get(1).getPrice(),0.0);
        Assert.assertEquals("Candy",testItem.get(1).getType());
        Assert.assertEquals(5,(int)testQuantity.get(1));

        Assert.assertEquals("C1",testItem.get(2).getPosition());
        Assert.assertEquals("Cola",testItem.get(2).getName());
        Assert.assertEquals(1.25,testItem.get(2).getPrice(),0.0);
        Assert.assertEquals("Drink",testItem.get(2).getType());
        Assert.assertEquals(5,(int)testQuantity.get(2));

        Assert.assertEquals("D1",testItem.get(3).getPosition());
        Assert.assertEquals("U-Chews",testItem.get(3).getName());
        Assert.assertEquals(0.85,testItem.get(3).getPrice(),0.0);
        Assert.assertEquals("Gum",testItem.get(3).getType());
        Assert.assertEquals(5,(int)testQuantity.get(3));

    }

    @Test
    public void generateSalesReport_Test() throws FileNotFoundException{
        SalesReport sp = new SalesReport();
        Scanner saleScanner = new Scanner(new File("generateSalesReportTest.txt"));
        VendingMachine.generateSalesReport(sp, saleScanner);
        List<String> names = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        for(Map.Entry<String, Integer> item : sp.report.entrySet()){
            names.add(item.getKey());
            quantity.add(item.getValue());
        }

        Assert.assertEquals("Stackers",names.get(0));
        Assert.assertEquals("Cowtales",names.get(1));
        Assert.assertEquals("Cola",names.get(2));
        Assert.assertEquals("Heavy",names.get(3));
        Assert.assertEquals("Cloud Popcorn",names.get(4));
        Assert.assertEquals("Dr. Salt",names.get(5));
        Assert.assertEquals("Little League Chew",names.get(6));
        Assert.assertEquals("Wonka Bar",names.get(7));
        Assert.assertEquals("Crunchie",names.get(8));
        Assert.assertEquals("Moonpie",names.get(9));
        Assert.assertEquals("Potato Crisps",names.get(10));
        Assert.assertEquals("Triplemint",names.get(11));
        Assert.assertEquals("Mountain Melter",names.get(12));
        Assert.assertEquals("U-Chews",names.get(13));
        Assert.assertEquals("Grain Waves",names.get(14));
        Assert.assertEquals("Chiclets",names.get(15));
        Assert.assertEquals(2,(int)quantity.get(0));
        Assert.assertEquals(0,(int)quantity.get(1));
        Assert.assertEquals(0,(int)quantity.get(2));
        Assert.assertEquals(0,(int)quantity.get(3));
        Assert.assertEquals(0,(int)quantity.get(4));
        Assert.assertEquals(0,(int)quantity.get(5));
        Assert.assertEquals(0,(int)quantity.get(6));
        Assert.assertEquals(0,(int)quantity.get(7));
        Assert.assertEquals(0,(int)quantity.get(8));
        Assert.assertEquals(0,(int)quantity.get(9));
        Assert.assertEquals(1,(int)quantity.get(10));
        Assert.assertEquals(0,(int)quantity.get(11));
        Assert.assertEquals(2,(int)quantity.get(12));
        Assert.assertEquals(1,(int)quantity.get(13));
        Assert.assertEquals(1,(int)quantity.get(14));
        Assert.assertEquals(0,(int)quantity.get(15));
        Assert.assertEquals(12.55,sp.currentSales,0.0);
    }

    @Test
    public void displayMenu_Test(){
        VendingMachine.displayMenu();
        Assert.assertEquals("(1) Display Vending Machine Items\n" +
                "(2) Purchase\n" +
                "(3) Exit\n", outContent.toString());
    }

    @Test
    public void displayPurchaseMenu_Test(){
        PurchaseItem purchase = new PurchaseItem();
        VendingMachine.displayPurchaseMenu(purchase);
        Assert.assertEquals("(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction\n" +
                "Current Money Provided: $0.0\n", outContent.toString());
    }

    @Test
    public void parseUserInput_Test_valid(){
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int testResult = VendingMachine.parseUserInput(new Scanner(System.in));
        Assert.assertEquals(1,testResult);
    }

    @Test
    public void parseUserInput_Test_invalid(){
        String input = "j";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int testResult = VendingMachine.parseUserInput(new Scanner(System.in));
        Assert.assertEquals(0,testResult);
    }

    @Test
    public void purchase_Test_1_and_3() throws Exception{
        String input = "1\n5\n3\nyes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        VendingMachineStock stock = new VendingMachineStock();
        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SalesReport salesReport = new SalesReport();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        int testResult = VendingMachine.purchase(stock, scanner, pw, salesReport, dateFormat);
        Assert.assertEquals(0,testResult);
        Assert.assertEquals("(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction\n" +
                "Current Money Provided: $0.0\n" +
                "Please enter your whole dollars:\n" +
                "(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction\n" +
                "Current Money Provided: $5.0\n" +
                "Are you sure you want to finish your transaction? Yes/No\n" +
                "your change is 20 quarters 0 dimes 0 nickels\n", outContent.toString());
    }

    @Test
    public void purchase_Test_2_and_3() throws Exception{
        String input = "2\nA1\nyes\n3\nyes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        VendingMachineStock stock = new VendingMachineStock();
        stock.addAnItem(new Chip("A1", "Potato Crisps", 3.05));

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SalesReport salesReport = new SalesReport();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        int testResult = VendingMachine.purchase(stock, scanner, pw, salesReport, dateFormat);
        Assert.assertEquals(0,testResult);
        Assert.assertEquals("(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction\n" +
                "Current Money Provided: $0.0\n" +
                "A1 Potato Crisps $3.05 Chip 5\n" +
                "Please choose an item by its position:\n" +
                "Did you mean to type A1? Enter Yes/No\n" +
                "Not enough money to buy this item\n" +
                "(1) Feed Money\n" +
                "(2) Select Product\n" +
                "(3) Finish Transaction\n" +
                "Current Money Provided: $0.0\n" +
                "Are you sure you want to finish your transaction? Yes/No\n" +
                "your change is 0 quarters 0 dimes 0 nickels\n", outContent.toString());
    }

    @Test
    public void selectProduct_Test_existPosition_and_balanceEnough() throws Exception {
        String input = "A1\nyes\n3\nyes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        VendingMachineStock stock = new VendingMachineStock();
        stock.addAnItem(new Chip("A1", "Potato Crisps", 3.05));

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SalesReport salesReport = new SalesReport();
        salesReport.addItem("Potato Crisps", 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        PurchaseItem item = new PurchaseItem();
        item.enterMoney(10.0);

        VendingMachine.selectProduct(stock, scanner, pw, salesReport, dateFormat, item);

        Assert.assertEquals("A1 Potato Crisps $3.05 Chip 5\n" +
                "Please choose an item by its position:\n" +
                "Did you mean to type A1? Enter Yes/No\n" +
                "Potato Crisps $3.05 $6.95 Crunch Crunch, Yum!\n", outContent.toString());
    }

    @Test
    public void selectProduct_Test_exist_position_and_balanceNegative() throws Exception {
        String input = "A1\nyes\n3\nyes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        VendingMachineStock stock = new VendingMachineStock();
        stock.addAnItem(new Chip("A1", "Potato Crisps", 3.05));

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SalesReport salesReport = new SalesReport();
        salesReport.addItem("Potato Crisps", 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        PurchaseItem item = new PurchaseItem();

        VendingMachine.selectProduct(stock, scanner, pw, salesReport, dateFormat, item);

        Assert.assertEquals("A1 Potato Crisps $3.05 Chip 5\n" +
                "Please choose an item by its position:\n" +
                "Did you mean to type A1? Enter Yes/No\n" +
                "Not enough money to buy this item\n", outContent.toString());
    }

    @Test
    public void selectProduct_Test_notexistPosition() throws Exception {
        String input = "KK\nyes\n3\nyes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        VendingMachineStock stock = new VendingMachineStock();
        stock.addAnItem(new Chip("A1", "Potato Crisps", 3.05));

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SalesReport salesReport = new SalesReport();
        salesReport.addItem("Potato Crisps", 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        PurchaseItem item = new PurchaseItem();

        VendingMachine.selectProduct(stock, scanner, pw, salesReport, dateFormat, item);

        Assert.assertEquals("A1 Potato Crisps $3.05 Chip 5\n" +
                "Please choose an item by its position:\n" +
                "Did you mean to type KK? Enter Yes/No\n" +
                "This position does not exist\n", outContent.toString());
    }

    @Test
    public void feedMoney_Test_wholeMoney() throws Exception{
        String input = "5.0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("MM/dd/yyyy HH:mm:ss a");
        PurchaseItem purchase = new PurchaseItem();

        VendingMachine.feedMoney(scanner, pw, sdf, purchase);
        Assert.assertEquals("Please enter your whole dollars:\n", outContent.toString());

    }

    @Test
    public void feedMoney_Test_nonWholeMoney() throws Exception{
        String input = "5.5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(file);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("MM/dd/yyyy HH:mm:ss a");
        PurchaseItem purchase = new PurchaseItem();

        VendingMachine.feedMoney(scanner, pw, sdf, purchase);
        Assert.assertEquals("Please enter your whole dollars:\n" +
                "This is not a whole dollar\n", outContent.toString());

    }
}
