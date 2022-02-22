package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

/**
 * @author Sarah Heckman
 */
public class CoffeeMaker {
	/** Array of recipes in coffee maker */
	private static RecipeBook recipeBook;
	/** Inventory of the coffee maker */
	private static Inventory inventory;

	/**
	 * Constructor for the coffee maker
	 *
	 */
	public CoffeeMaker() {
		recipeBook = new RecipeBook();
		inventory = new Inventory();
	}

	/**
	 * Returns true if the recipe is added to the
	 * list of recipes in the CoffeeMaker and false
	 * otherwise.
	 * 
	 * @param r
	 * @return boolean
	 */
	public boolean addRecipe(Recipe r) {
		return recipeBook.addRecipe(r);
	}

	/**
	 * Returns the name of the successfully deleted recipe
	 * or null if the recipe cannot be deleted.
	 * 
	 * @param recipeToDelete
	 * @return String
	 */
	public String deleteRecipe(int recipeToDelete) {
		return recipeBook.deleteRecipe(recipeToDelete);
	}

	/**
	 * Returns the name of the successfully edited recipe
	 * or null if the recipe cannot be edited.
	 * 
	 * @param recipeToEdit
	 * @param r
	 * @return String
	 */
	public String editRecipe(int recipeToEdit, Recipe r) {
		return recipeBook.editRecipe(recipeToEdit, r);
	}

	/**
	 * Returns true if inventory was successfully added
	 * 
	 * @param amtCoffee
	 * @param amtMilk
	 * @param amtSugar
	 * @param amtChocolate
	 * @return boolean
	 */
	public synchronized void addInventory(String amtCoffee, String amtMilk, String amtSugar, String amtChocolate)
			throws InventoryException {
		inventory.addCoffee(amtCoffee);
		inventory.addMilk(amtMilk);
		inventory.addSugar(amtSugar);
		inventory.addChocolate(amtChocolate);
	}

	/**
	 * Returns the inventory of the coffee maker
	 * 
	 * @return Inventory
	 */
	public synchronized String checkInventory() {
		return inventory.toString();
	}

	/**
	 * Returns the change of a user's beverage purchase, or
	 * the user's money if the beverage cannot be made
	 * 
	 * @param r
	 * @param amtPaid
	 * @return int
	 */
	public synchronized int makeCoffee(int recipeToPurchase, int amtPaid) {
		int change = 0;
		// 1. no recipe, try purchase. 2. recipe, price < amtpaid 3.price == amtpaid

		if (getRecipes()[recipeToPurchase] == null) { // does it contain the recipe
			change = amtPaid; // set change to same as paid
		} else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) { // get price of recipe and check that it is
																			// less thenpaid
			if (inventory.useIngredients(getRecipes()[recipeToPurchase])) { // consume the ingredients for the recipe
				change = amtPaid - getRecipes()[recipeToPurchase].getPrice();// calc the change
			} else {
				change = amtPaid; // not enough money
			}
		} else {
			change = amtPaid; // there is no inventory
		}

		return change; //
	}

	/**
	 * Returns the list of Recipes in the RecipeBook.
	 * 
	 * @return Recipe []
	 */
	public synchronized Recipe[] getRecipes() {
		return recipeBook.getRecipes();
	}
}
