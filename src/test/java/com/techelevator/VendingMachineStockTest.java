package com.techelevator;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.techelevator.Item;

public class VendingMachineStockTest {
    VendingMachineStock stock;

    @Before
    public void setup(){
        stock = new VendingMachineStock();
    }
    // The main file has already guaranteed that changeStock will have a position in the map
    // so it cannot be null
    @Test
    public void changeStock_delete_1_from_candy(){
        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        stock.changeStock("1");

        //Assert
        Assert.assertEquals(4, stock.getQuantity("1"));


    }

    @Test
    public void getItem_returns_correct_Item_candy_from_Map(){

        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        Item result = stock.getItem("1");

        //Assert
        Assert.assertEquals(candy.getPosition(), result.getPosition());

    }

    @Test
    public void getQuantity_returns_5_for_the_quantity(){

        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        int result = stock.getQuantity("1");

        //Assert
        Assert.assertEquals(5, result);

    }
    @Test
    public void doesPositionExist_return_true_for_1_existing(){
        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        boolean result = stock.doesPositionExist("1");

        //Assert
        Assert.assertEquals(true, result);
    }

    @Test
    public void displayAllItems_displays_all_items_in_the_list(){
        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        String result = stock.displayAllItems();
        String expected =  candy.getPosition() + " " + candy.getName() + " $" + candy.getPrice() + " " + candy.getType() + " " + 5 + "\n";
        expected+=chip.getPosition() + " " + chip.getName() + " $" + chip.getPrice() + " " + chip.getType() + " " + 5 + "\n";

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void reportWriter(){
        //Arrange
        Item candy = new Item("1", "test", 4.0);
        Item chip = new Item("2", "test2", 3.5);
        stock.addAnItem(candy);
        stock.addAnItem(chip);

        //Act
        String result = stock.reportWriter();
        String expected = candy.getName() + "|" + "0" + "\n";
        expected += chip.getName() + "|" + "0" + "\n";

        //Assert
        Assert.assertEquals(expected, result);


    }





}
