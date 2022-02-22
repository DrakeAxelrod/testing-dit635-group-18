package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetPriceTest {
    private Recipe r1;

    @BeforeEach
    public void init() throws Exception {
        // Set up for r1
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("10");
    }

    @Test
    public void testSetPricePositiveNumberTrue() {
        try {
            r1.setPrice("5");
        } catch (RecipeException e) {
            fail("Failed to set price.");
        }
        assertEquals(r1.getPrice(), 5);
    }

    @Test
    public void testSetPriceZeroTrue() {
        try {
            r1.setPrice("0");
        } catch (RecipeException e) {
            fail("Failed to set price.");
        }
        assertEquals(r1.getPrice(), 0);
    }

    @Test
    public void testSetPricePositiveDecimalExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("0.5");
        });
    }

    @Test
    public void testSetPriceNegativeDecimalExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("-1210.5");
        });
    }

    @Test
    public void testSetPriceLargerThanMaxIntExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("2147483648");
        });
    }

    @Test
    public void testSetPriceSmallerThanMinIntExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("-2147483649");
        });
    }

    @Test
    public void testSetPriceNegativeNumberExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("-5");
        });
    }

    @Test
    public void testSetPriceNonNumericExpectFail() {
        assertThrows(RecipeException.class, () -> {
            r1.setPrice("1yqh");
        });
    }
}
