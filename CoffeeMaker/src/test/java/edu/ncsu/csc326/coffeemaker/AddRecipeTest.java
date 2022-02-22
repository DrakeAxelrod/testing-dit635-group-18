package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddRecipeTest {

    private RecipeBook book;
    private Recipe r1;
    private Recipe r2;

    @BeforeEach
    private void setup() throws Exception {
        book = new RecipeBook();

        // Set up for r2
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("10");

        // Set up for r2
        r2 = new Recipe();
        r2.setName("Mocha");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("75");
    }

    @Test
    public void testAddNewRecipe() {
        boolean added = book.addRecipe(r1);
        assertTrue(added);
    }

    @Test
    public void testAddRecipeThatExists() {
        book.addRecipe(r1);
        boolean added = book.addRecipe(r1);
        assertFalse(added);
    }
}
