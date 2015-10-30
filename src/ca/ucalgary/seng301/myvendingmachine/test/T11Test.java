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

public class T11Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {

		// Construct the vending machine
		int[] coinKinds = { 100, 5, 25, 10 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
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

		// Compare the extraction with expected results
		List<Object> extractedItems2 = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems2 = TestUtilities.parseExtraction(extractedItems2);
		Object[] expectedExtraction2 = { 0 };

		assertArrayEquals(expectedExtraction2, actualExtractedItems2);

		// Compare vending machine stored contents with expected output
		Object[] actualContents1 = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents1 = { 65, 0, "Coke", "water", "stuff" };
		assertArrayEquals(expectedContents1, actualContents1);

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

		// Construct the vending machine
		int[] coinKinds = { 100, 5, 25, 10 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);

		// Configure the vending machine
		List<String> popNames1 = new ArrayList<String>();
		popNames1.add("Coke");
		popNames1.add("water");
		popNames1.add("stuff");

		List<Integer> popCosts1 = new ArrayList<Integer>();
		popCosts1.add(250);
		popCosts1.add(250);
		popCosts1.add(205);

		tMachine.configure(popNames1, popCosts1);

		// Configure the vending machine
		List<String> popNames2 = new ArrayList<String>();
		popNames2.add("A");
		popNames2.add("B");
		popNames2.add("C");

		List<Integer> popCosts2 = new ArrayList<Integer>();
		popCosts2.add(5);
		popCosts2.add(10);
		popCosts2.add(25);

		tMachine.configure(popNames2, popCosts2);

		// Compare vending machine stored contents with expected output
		Object[] actualContents3 = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents3 = { 0, 0 };
		assertArrayEquals(expectedContents3, actualContents3);

		// Load the vending machine
		List<Integer> coinCounts1 = new ArrayList<Integer>();
		coinCounts1.add(0);
		coinCounts1.add(1);
		coinCounts1.add(2);
		coinCounts1.add(1);

		List<Integer> popCanCounts1 = new ArrayList<Integer>();
		popCanCounts1.add(1);
		popCanCounts1.add(1);
		popCanCounts1.add(1);

		TestUtilities.stock(coinCounts1, popCanCounts1, tMachine);

		// Insert 10, 5, 10
		try {
			tMachine.getCoinSlot().addCoin(new Coin(10));
			tMachine.getCoinSlot().addCoin(new Coin(5));
			tMachine.getCoinSlot().addCoin(new Coin(10));
		} catch (DisabledException e) {
			e.printStackTrace();
		}

		// Press selection button two
		tMachine.getSelectionButton(2).press();

		// Compare the extraction with expected results
		List<Object> extractedItems4 = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems4 = TestUtilities.parseExtraction(extractedItems4);
		Object[] expectedExtraction4 = { 0, "C" };

		assertArrayEquals(expectedExtraction4, actualExtractedItems4);

		// Compare vending machine stored contents with expected output
		Object[] actualContents4 = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents4 = { 90, 0, "A", "B" };
		assertArrayEquals(expectedContents4, actualContents4);

	}
}
