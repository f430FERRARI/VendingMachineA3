package ca.ucalgary.seng301.myvendingmachine.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ucalgary.seng301.myvendingmachine.VendingMachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class T03Test {

	VendingMachine tMachine;

	@Before
	public void setUp() throws Exception {
		// Construct the vending machine
		int[] coinKinds = { 5, 10, 25, 100 };
		tMachine = new VendingMachine(coinKinds, 3, 10, 10, 10);
		new VendingMachineLogic(tMachine);
	}

	@Test
	public void test() {
		// Compare the extraction with expected results
		List<Object> extractedItems = Arrays.asList(tMachine.getDeliveryChute().removeItems());
		Object[] actualExtractedItems = TestUtilities.parseExtraction(extractedItems);
		Object[] expectedExtraction = { 0 };

		assertArrayEquals(expectedExtraction, actualExtractedItems);

		// Compare vending machine stored contents with expected output
		Object[] actualContents = TestUtilities.parseContents(TestUtilities.emptyContents(tMachine));
		Object[] expectedContents = { 0, 0 };
		assertArrayEquals(expectedContents, actualContents);
	}

}
