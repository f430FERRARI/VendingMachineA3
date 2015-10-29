package ca.ucalgary.seng301.myvendingmachine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class T07Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {

		// Construct the vending machine
		int[] coinKinds = { 5, 10, 25, 100 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);

		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("A");
		popNames.add("B");
		popNames.add("C");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(5);
		popCosts.add(10);
		popCosts.add(25);

		tMachine.configure(popNames, popCosts);

		// Load the vending machine
		List<Integer> coinCounts = new ArrayList<Integer>();
		coinCounts.add(1);
		coinCounts.add(1);
		coinCounts.add(2);
		coinCounts.add(0);

		List<Integer> popCanCounts = new ArrayList<Integer>();
		popCanCounts.add(1);
		popCanCounts.add(1);
		popCanCounts.add(1);

		TestUtilities.stock(coinCounts, popCanCounts, tMachine);
	}

	@Test
	public void test() {

		// TODO: IS this the start of the test?
		// Reconfigure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("Coke");
		popNames.add("water");
		popNames.add("stuff");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(250);
		popCosts.add(250);
		popCosts.add(205);

		tMachine.configure(popNames, popCosts);

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
		Object[] expectedExtraction2 = { 50, "A" };

		assertArrayEquals(expectedExtraction2, actualExtractedItems2);

		// Compare vending machine stored contents with expected output
		Object[] actualContents1 = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents1 = { 315, 0, "B", "C" };
		assertArrayEquals(expectedContents1, actualContents1);

		// Load the vending machine
		List<Integer> coinCounts = new ArrayList<Integer>();
		coinCounts.add(1);
		coinCounts.add(1);
		coinCounts.add(2);
		coinCounts.add(0);

		List<Integer> popCanCounts = new ArrayList<Integer>();
		popCanCounts.add(1);
		popCanCounts.add(1);
		popCanCounts.add(1);

		TestUtilities.stock(coinCounts, popCanCounts, tMachine);

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
		List<Object> extractedItems3 = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems3 = TestUtilities.parseExtraction(extractedItems3);
		Object[] expectedExtraction3 = { 50, "Coke" };

		assertArrayEquals(expectedExtraction3, actualExtractedItems3);

		// Compare vending machine stored contents with expected output
		Object[] actualContents2 = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents2 = { 315, 0, "water", "stuff" };
		assertArrayEquals(expectedContents2, actualContents2);
	}
}
