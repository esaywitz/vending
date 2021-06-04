package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void getEatingMessage_Test(){
        Item item = new Item("I2","item", 5.5);
        String testValue = item.getEatingMessage();
        String exceptValue = null;
        Assert.assertEquals(exceptValue,testValue);
    }

    @Test
    public void getPrice_Test(){
        Item item = new Item("I2","item", 5.5);
        double testValue = item.getPrice();
        double exceptValue = 5.5;
        Assert.assertEquals(exceptValue,testValue,0.0);
    }

    @Test
    public void getName_Test(){
        Item item = new Item("I2","item", 5.5);
        String testValue = item.getName();
        String exceptValue = "item";
        Assert.assertEquals(exceptValue,testValue);
    }

    @Test
    public void getPosition_Test(){
        Item item = new Item("I2","item", 5.5);
        String testValue = item.getPosition();
        String exceptValue = "I2";
        Assert.assertEquals(exceptValue,testValue);
    }

    @Test
    public void getType_Test(){
        Item item = new Item("I2","item", 5.5);
        String testValue = item.getType();
        String exceptValue = null;
        Assert.assertEquals(exceptValue,testValue);
    }
}
