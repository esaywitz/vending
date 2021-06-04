package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ChipTest {

    @Test
    public void getEatingMessage_Test(){
        Chip chip = new Chip("A1","chip", 3.5);
        String testValue = chip.getEatingMessage();
        String expectValue = "Crunch Crunch, Yum!";
        Assert.assertEquals(expectValue, testValue);
    }

    @Test
    public void getType_Test(){
        Chip chip = new Chip("A1","chip", 3.5);
        String testValue = chip.getType();
        String expectValue = "Chip";
        Assert.assertEquals(expectValue, testValue);
    }
}
