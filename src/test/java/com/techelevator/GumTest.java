package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class GumTest {

    @Test
    public void getEatingMessage_Test(){
        Gum gum = new Gum("A1","gum", 3.5);
        String testValue = gum.getEatingMessage();
        String expectValue = "Chew Chew, Yum!";
        Assert.assertEquals(expectValue, testValue);
    }

    @Test
    public void getType_Test(){
        Gum gum = new Gum("A1","gum", 3.5);
        String testValue = gum.getType();
        String expectValue = "Gum";
        Assert.assertEquals(expectValue, testValue);
    }
}
