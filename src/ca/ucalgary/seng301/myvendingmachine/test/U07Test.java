package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U07Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {
		// Construct the vending machine
		int[] coinKinds = { 1 };
		tMachine = new VendingMachine(coinKinds, 1, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}

	@Test(expected = IllegalArgumentException.class) // TODO
	public void test() {
		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(1);

		tMachine.configure(popNames, popCosts);
	}
}
