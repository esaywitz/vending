package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReportTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void addItem_Test(){
        Map<String, Integer> expectReport = new HashMap<>();
        SalesReport testReport = new SalesReport();
        testReport.addItem("chip",5);
        expectReport.put("chip", 5);
        Assert.assertEquals(expectReport, testReport.report);
    }

    @Test
    public void updateQuanity_Test(){
        Map<String, Integer> expectReport = new HashMap<>();
        SalesReport testReport = new SalesReport();
        testReport.report.put("chip",5);
        testReport.updateQuanity("chip");
        expectReport.put("chip", 6);
        Assert.assertEquals(expectReport, testReport.report);
    }

    @Test
    public void displaySales_Test() throws FileNotFoundException {
        File textFile = new File("SalesReportDisplaySalesTest.txt");
        PrintWriter pw = new PrintWriter(textFile);
        SalesReport testReport = new SalesReport();
        testReport.report.put("chip",5);
        testReport.displaySales(pw);
        Scanner pwScanner = new Scanner(textFile);
        String testString1 = "";
        String testString2 = "";
        int count = 0;
        while(count < 1 && pwScanner.hasNext()){
            count++;
            testString1 = pwScanner.nextLine();
        }
        while(count < 2 && pwScanner.hasNext()){
            count++;
            testString2 = pwScanner.nextLine();
        }
        Assert.assertEquals("chip|5", testString1);
        Assert.assertEquals("0.0", testString2);

    }

    @Test
    public void displayOnConsole_Test(){
        SalesReport testReport = new SalesReport();
        testReport.report.put("chip",5);
        testReport.displayOnConsole();
        Assert.assertEquals("chip|5\n" + "TOTAL SALES: $0.0\n", outContent.toString());
    }

    @Test
    public void setCurrentSales_Test(){
        SalesReport testReport = new SalesReport();
        testReport.setCurrentSales(5.5);
        double testValue = testReport.currentSales;
        Assert.assertEquals(5.5, testValue, 0.0);
    }

    @Test
    public void updateCurrentSales_Test(){
        SalesReport testReport = new SalesReport();
        testReport.currentSales = 6.0;
        testReport.updateCurrentSales(4.5);
        double testValue = testReport.currentSales;
        Assert.assertEquals(10.5, testValue, 0.0);
    }
}
