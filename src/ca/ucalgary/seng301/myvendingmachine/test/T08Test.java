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

public class T08Test {
	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {

		// Construct the vending machine
		int[] coinKinds = { 5, 10, 25, 100 };
		tMachine = new VendingMachine(coinKinds, 1, 10, 10, 10);
		new VendingMachineLogic(tMachine);

		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("stuff");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(140);

		tMachine.configure(popNames, popCosts);

		// Load the vending machine
		List<Integer> coinCounts = new ArrayList<Integer>();
		coinCounts.add(0);
		coinCounts.add(5);
		coinCounts.add(1);
		coinCounts.add(1);

		List<Integer> popCanCounts = new ArrayList<Integer>();
		popCanCounts.add(1);

		TestUtilities.stock(coinCounts, popCanCounts, tMachine);
	}

	@Test
	public void test() {

		// Insert 100, 100, 25, 25
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
		List<Object> extractedItems = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems = TestUtilities.parseExtraction(extractedItems);
		Object[] expectedExtraction = { 155, "stuff" };

		assertArrayEquals(expectedExtraction, actualExtractedItems);

		// Compare vending machine stored contents with expected output
		Object[] actualContents = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents = { 320, 0 };
		assertArrayEquals(expectedContents, actualContents);
	}
}
