package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class CandyTest {

    @Test
    public void getEatingMessage_Test(){
        Candy candy = new Candy("A1","candy", 3.5);
        String testValue = candy.getEatingMessage();
        String expectValue = "Munch Munch, Yum!";
        Assert.assertEquals(expectValue, testValue);
    }

    @Test
    public void getType_Test(){
        Candy candy = new Candy("A1","candy", 3.5);
        String testValue = candy.getType();
        String expectValue = "Candy";
        Assert.assertEquals(expectValue, testValue);
    }
}
