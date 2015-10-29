package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.SimulationException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U03Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {
		// Construct the vending machine
		int[] coinKinds = { 5, 10, 25, 100 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}

	@Test(expected = SimulationException.class)		//TODO
	public void test() {
		// Configure the vending machine
		List<String> popNames = new ArrayList<String>();
		popNames.add("Coke");
		popNames.add("water");

		List<Integer> popCosts = new ArrayList<Integer>();
		popCosts.add(250);
		popCosts.add(250);

		tMachine.configure(popNames, popCosts);
	}

}
