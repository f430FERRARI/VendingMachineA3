package ca.ucalgary.seng301.myvendingmachine.test;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class U06Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {
		// Construct the vending machine
		int[] coinKinds = { 5,10,25,100 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class) // TODO
	public void test() {
		// Press selection button three
				tMachine.getSelectionButton(3).press();
	}
}
