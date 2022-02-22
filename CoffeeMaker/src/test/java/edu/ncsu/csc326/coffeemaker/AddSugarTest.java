package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class AddSugarTest {

    private Inventory inv;

    @BeforeEach
    private void setup() throws Exception {
        inv = new Inventory();
    }

    @Test
    public void testAddSugarPositiveNumber() {
        try {
            inv.addSugar("4");
        } catch (InventoryException e) {
            fail("Failed to add sugar.");
        }
        assertEquals(19, inv.getSugar());
    }

    @Test
    public void testAddSugarNegativeNumber() {
        assertThrows(InventoryException.class, () -> {
            inv.addSugar("-1");
        });
    }

    @Test
    public void testAddSugarPositiveDecimalExpectFail() {
        assertThrows(InventoryException.class, () -> {
            inv.addSugar("0.5");
        });
    }

    @Test
    public void testAddSugarNonNumericExpectFail() {
        assertThrows(InventoryException.class, () -> {
            inv.addSugar("1yqh");
        });
    }
}
