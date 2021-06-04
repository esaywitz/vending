package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class BeverageTest {

    @Test
    public void getEatingMessage_Test(){
        Beverage beverage = new Beverage("A1","drink", 3.5);
        String testValue = beverage.getEatingMessage();
        String expectValue = "Glug Glug, Yum!";
        Assert.assertEquals(expectValue, testValue);
    }

    @Test
    public void getType_Test(){
        Beverage beverage = new Beverage("A1","drink", 3.5);
        String testValue = beverage.getType();
        String expectValue = "Drink";
        Assert.assertEquals(expectValue, testValue);
    }
}
