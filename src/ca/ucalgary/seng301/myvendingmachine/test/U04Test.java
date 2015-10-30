package ca.ucalgary.seng301.myvendingmachine.test;

import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.SimulationException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U04Test {

	VendingMachine tMachine;

	@Test(expected = SimulationException.class) 
	public void test() {
		// Construct the vending machine
		int[] coinKinds = { 1, 1 };
		tMachine = new VendingMachine(coinKinds, 1, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}
}
