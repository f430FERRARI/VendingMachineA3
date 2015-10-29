package ca.ucalgary.seng301.myvendingmachine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.SimulationException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U02Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {
		// Construct the vending machine
		int[] coinKinds = { 5, 10, 25, 100 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}

	@Test(expected = SimulationException.class) // TODO: Returns the wrong
														// exception
	public void test() {

		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("Coke");
		popNames.add("water");
		popNames.add("stuff");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(250);
		popCosts.add(250);

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

		// Compare vending machine stored contents with expected output
		Object[] actualContents = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents = { 65, 0, "Coke", "water", "stuff" };
		assertArrayEquals(expectedContents, actualContents); //TODO

	}

}
