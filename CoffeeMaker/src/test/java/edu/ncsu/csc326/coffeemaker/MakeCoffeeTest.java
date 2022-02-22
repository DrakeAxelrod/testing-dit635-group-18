package edu.ncsu.csc326.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MakeCoffeeTest {

    private CoffeeMaker cm;
    private Recipe r;

    @BeforeEach
    private void setup() throws Exception {
        cm = new CoffeeMaker();
        r = new Recipe();
        r.setAmtChocolate("1");
        r.setPrice("10");
        r.setAmtMilk("1");
        r.setAmtSugar("1");
        r.setAmtCoffee("1");
    }

    @Test
    public void testMakeWithoutRecipes() {
        int money = 10;
        int change = cm.makeCoffee(0, money);
        assertEquals(money, change);
    }

    @Test
    public void testMakeWithExactAmount() {
        cm.addRecipe(r);
        int change = cm.makeCoffee(0, 10);
        assertEquals(0, change);
    }

    @Test
    public void testMakeWithHigher() {
        cm.addRecipe(r);
        int change = cm.makeCoffee(0, 15);
        assertEquals(5, change);
    }
}
