package ca.ucalgary.seng301.myvendingmachine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.TestUtilities;
import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class T05Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {

		// Construct the vending machine
		int[] coinKinds = { 100, 5, 25, 10 };
		tMachine = new VendingMachine(coinKinds, 3, 2, 10, 10);
		new VendingMachineLogic(tMachine);

		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("Coke");
		popNames.add("water");
		popNames.add("stuff");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(250);
		popCosts.add(250);
		popCosts.add(205);

		tMachine.configure(popNames, popCosts);

		// Load the vending machine
		List<Integer> coinCounts = new ArrayList<Integer>();
		coinCounts.add(0);
		coinCounts.add(1);
		coinCounts.add(2);
		coinCounts.add(1);

		List<Integer> popCanCounts = new ArrayList<Integer>();
		popCanCounts.add(1);
		popCanCounts.add(1);
		popCanCounts.add(1);

		TestUtilities.stock(coinCounts, popCanCounts, tMachine);
	}

	@Test
	public void test() {

		// Press selection button zero
		tMachine.getSelectionButton(0).press();

		// Compare the extraction with expected results
		List<Object> extractedItems1 = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems1 = TestUtilities.parseExtraction(extractedItems1);
		Object[] expectedExtraction1 = { 0 };

		assertArrayEquals(expectedExtraction1, actualExtractedItems1);

		// Insert 100, 100, 100
		try {
			tMachine.getCoinSlot().addCoin(new Coin(100));
			tMachine.getCoinSlot().addCoin(new Coin(100));
			tMachine.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			e.printStackTrace();
		}

		// Press selection button zero
		tMachine.getSelectionButton(0).press();

		// Compare the extraction with expected results
		List<Object> extractedItems2 = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems2 = TestUtilities.parseExtraction(extractedItems2);
		Object[] expectedExtraction2 = { 50, "Coke" };

		assertArrayEquals(expectedExtraction2, actualExtractedItems2);

		// Compare vending machine stored contents with expected output
		Object[] actualContents = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents = { 215, 100, "water", "stuff" };
		assertArrayEquals(expectedContents, actualContents);
	}
}
