package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;

	@BeforeEach
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		// Set up for r1
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

		// too big setup
		r3 = new Recipe();
		r3.setName("BigCoffee");
		r3.setAmtChocolate("1000000");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("1");
		r3.setAmtSugar("1");
		r3.setPrice("75");
	}

	@Test
	public void testAddNewRecipeWithAllParameters() {
		cm.addRecipe(r1);
		Recipe recipes[] = cm.getRecipes();
		assertEquals(r1, recipes[0]);
	}

	@Test
	public void testRecipeBookIndexOutOfBounds() {
		assertDoesNotThrow(() -> {
			IntStream.range(0, 5).forEachOrdered(n -> {
				cm.addRecipe(new Recipe());
			});
			Recipe recipes[] = cm.getRecipes();
			return recipes[5];
		}, "threw an exception - Index out of bounds");
	}

	@Test
	public void testAddRecipeWithEmptyParameter() {
		Recipe r4 = new Recipe();
		r4.setName("GoodOleJoe");
		Exception exception = assertThrows(RecipeException.class, () -> {
			r4.setAmtChocolate("");
			r4.setAmtCoffee("3");
			r4.setAmtMilk("1");
			r4.setAmtSugar("1");
			r4.setPrice("20");
		});
		String expectedMessage = "must be a positive integer";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testAddNewRecipeWithLargeNumbers() { // this shouldn't be added, so the assert should change
		cm.addRecipe(r3);
		int choc = cm.getRecipes()[0].getAmtChocolate();
		assertEquals("1000000", choc);
	}

	@Test
	public void testDeleteRecipe() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.deleteRecipe(0);
		Recipe recipes[] = cm.getRecipes();
		assertEquals(r2, recipes[0]);
	}

	@Test
	public void testDeleteRecipeThatDoesntExist() {
		cm.deleteRecipe(0);
		// delete recipe returns null if there is nothing at that index
		assertEquals(null, cm.deleteRecipe(0));
	}

	@Test
	public void testDeleteAndTryPurchase() {
		cm.addRecipe(r1);
		cm.deleteRecipe(0);
		int money = cm.makeCoffee(1, 10); // here the 'change' is returned
		assertEquals(10, money); // here there should be as much money as the user put in, because the purchase
									// should fail
	}

	@Test
	public void testShouldFailEditRecipeUsingTextInput() {
		cm.addRecipe(r1);
		assertThrows(RecipeException.class, () -> {
			cm.getRecipes()[0].setAmtChocolate("f");
		});
	}

	@Test
	public void testEditRecipeWithCorrectInput() {
		cm.addRecipe(r1);
		String oldRecipe = cm.editRecipe(0, r2);
		assertEquals("Coffee", oldRecipe);
	}

	@Test // Adding sugar would fail because the amount in the program takes 0 or less
	public void testInventoryIsUpdatedAfterAdding() {
		try {
			cm.addInventory("1", "1", "1", "1");
		} catch (InventoryException e) {
			fail("Inventory failed to update");
		}
		String expected = ("Coffee: 16\nMilk: 16\nSugar: 16\nChocolate: 16\n");
		String inventory = cm.checkInventory();
		assertEquals(expected, inventory);
	}

	@Test
	public void testInventoryIsUpdatedAfterPurchase() {
		cm.addRecipe(r1);
		cm.makeCoffee(0, 10);
		String expected = "Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n";
		String inventory = cm.checkInventory();
		assertEquals(expected, inventory);
	}

	@Test
	public void testShouldFailAddLargeAmountsToInventory() {
		String currentInv = cm.checkInventory();
		try {
			cm.addInventory("1000000", "1000000", "0", "100000");
		} catch (Exception e) {
			fail("No exception should be thrown");
		}
		String inventory = cm.checkInventory();
		assertEquals(currentInv, inventory);
	}

	@Test
	public void testAddNegativeAmountsToInventory() {
		assertThrows(InventoryException.class, () -> {
			cm.addInventory("-5", "0", "0", "0"); // testInventoryAdded
		});
	}

	@Test
	public void addFailedRecipeThenPurchase() {
		Recipe r = new Recipe();
		int money = 0;
		try {
			r.setAmtChocolate("not number");
			r.setAmtCoffee("not number");
			r.setAmtMilk("not number");
			r.setAmtSugar("not number");
			r.setPrice("not number");
			cm.addRecipe(r);
		} catch (RecipeException e) {
			money = cm.makeCoffee(0, 10);
		} finally {
			assertEquals(10, money);
		}
	}

	@Test
	public void testPurchaseItemThatDoesntExist() {
		int money = cm.makeCoffee(0, 10);
		assertEquals(10, money);
	}

	@Test
	public void purchaseUsingTextInput() {
		cm.addRecipe(r1);
		assertThrows(NumberFormatException.class, () -> {
			Main m = new Main();
			Class<?>[] parameterType = null;
			// yassssssss
			Method method = Main.class.getDeclaredMethod("recipeListSelection", parameterType);
			method.setAccessible(true);
			try {
				method.invoke(m, "String");
			} catch (NumberFormatException e) {
				fail("Failed to handle string input");
			}
		});
	}
}
