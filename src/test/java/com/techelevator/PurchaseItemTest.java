package com.techelevator;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.techelevator.Item;

public class PurchaseItemTest {

    PurchaseItem item;

    @Before
    public void setup(){
        item = new PurchaseItem();
    }

    @Test
    public void selectAProduct_returns_correct_log_String(){
        item.enterMoney(10);

        Item newItem = new Item("1", "test", 3);

        String result = item.selectAProduct(newItem);
        String expected = newItem.getName() + " " + newItem.getPosition() + " $" + "10.0" + " $" + "7.0";

        Assert.assertEquals(expected,result);
    }

    @Test
    public void finishTransaction_returns_correct_String_for_log(){
        item.enterMoney(10);



        String result = item.finishTransaction();
        String expected = "GIVE CHANGE: $" + "10.0" + " $0.0";

        Assert.assertEquals(expected,result);
    }
}
