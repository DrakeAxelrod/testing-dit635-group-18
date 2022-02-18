package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;

	@Before
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		// Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");

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
	public void testAddNewRecipeWithAllParameters() {
		try {
			cm.addInventory("2", "2", "2", "1"); // Coffee, Milk, Sugar, Chocolate
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
		String inventory = cm.checkInventory();
		String expected = "Coffee: 17\nMilk: 17\nSugar: 15\nChocolate: 17\n";
		// We start with 15 in inventory, then added some.
		assertEquals(expected, inventory);
	}

	@Test
	public void testAddNewRecipeWithExistingName() {
		cm.addRecipe(r1);
		cm.addRecipe(r1);
		Recipe recipes[] = cm.getRecipes();
		assertEquals(1, recipes.length);
	}

	@Test
	public void RecipeExceptionThrown_thenExpectationSatisfied() {
		r2 = new Recipe();
		r2.setName("Mocha");
		Exception exception = assertThrows(RecipeException.class, () -> {
		r2.setAmtChocolate("");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		});
		String expectedMessage = "must be a positive integer";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testAddNewRecipeWithLargeNumbers() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testDeleteRecipe() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testDeleteRecipeThatDoesntExist() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testDeleteAndTryPurchase() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testEditRecipeUsingTextInput() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testEditRecipeWithCorrectInput() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testInventoryIsUpdatedAfterUpdate() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testInventoryIsUpdatedAfterPurchase() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testAddLargeAmountsToInventory() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testAddNegativeAmountsToInventory() {
		try {
			// Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void addFailedRecipeThenPurchase() {
		try {
			 //Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void testPurchaseItemThatDoesntExist() {
		try {
			 //Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

	@Test
	public void purchaseUsingTextInput() {
		try {
			 //Coffee, Milk, Sugar, Chocolate
		} catch (Exception e) {
			fail("   ");
		}
	}

}
