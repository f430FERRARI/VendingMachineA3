package ca.ucalgary.seng301.myvendingmachine.test;

import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.SimulationException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U05Test {

	VendingMachine tMachine;

	@Test(expected = SimulationException.class) // TODO
	public void test() {
		// Construct the vending machine
		int[] coinKinds = { 0 };
		tMachine = new VendingMachine(coinKinds, 1, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}
}
