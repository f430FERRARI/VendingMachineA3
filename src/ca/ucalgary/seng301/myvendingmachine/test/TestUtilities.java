package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.ArrayList;
import java.util.List;

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.PopCan;
import ca.ucalgary.seng301.vendingmachine.VendingMachineStoredContents;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinRack;
import ca.ucalgary.seng301.vendingmachine.hardware.PopCanRack;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class TestUtilities {

	public static void stock(List<Integer> coinCounts, List<Integer> popCanCounts, VendingMachine vm) {
		int numberOfCoinRacks = vm.getNumberOfCoinRacks();
		int numberOfPopCanRacks = vm.getNumberOfPopCanRacks();

		if (coinCounts.size() != numberOfCoinRacks)
			throw new IllegalArgumentException(
					"The size of the coinCounts list must be identical to the number of coin racks in the machine");
		if (popCanCounts.size() != numberOfPopCanRacks)
			throw new IllegalArgumentException(
					"The size of the popCanCounts list must be identical to the number of pop can racks in the machine");

		int i = 0;
		for (Integer coinCount : coinCounts) {
			CoinRack cr = vm.getCoinRack(i);
			for (int count = 0; count < coinCount; count++)
				cr.loadWithoutEvents(new Coin(vm.getCoinKindForRack(i)));
			i++;
		}

		i = 0;
		for (Integer popCanCount : popCanCounts) {
			PopCanRack pcr = vm.getPopCanRack(i);
			for (int count = 0; count < popCanCount; count++)
				pcr.loadWithoutEvents(new PopCan(vm.getPopKindName(i)));
			i++;
		}
	}

	public static VendingMachineStoredContents emptyContents(VendingMachine vm) {
		VendingMachineStoredContents contents = new VendingMachineStoredContents();
		contents.paymentCoinsInStorageBin.addAll(vm.getStorageBin().unloadWithoutEvents());
		for (int i = 0; i < vm.getNumberOfPopCanRacks(); i++) {
			PopCanRack pcr = vm.getPopCanRack(i);
			contents.unsoldPopCans.add(new ArrayList<>(pcr.unloadWithoutEvents()));
		}
		for (int i = 0; i < vm.getNumberOfCoinRacks(); i++) {
			CoinRack cr = vm.getCoinRack(i);
			contents.unusedCoinsForChange.add(new ArrayList<>(cr.unloadWithoutEvents()));
		}
		return contents;
	}

	public static Object[] parseExtraction(List<Object> extractedItems) {
		int popCanCount = 0;
		for (Object item : extractedItems) {
			if (item instanceof PopCan) {
				popCanCount++;
			}
		}
		Object[] testResult = new Object[1 + popCanCount];

		int totalChange = 0;
		int i = 1;
		for (Object item : extractedItems) {
			if (item instanceof Coin) {
				totalChange += ((Coin) item).getValue();
			} else if (item instanceof PopCan) {
				testResult[i++] = ((PopCan) item).getName();
			}
			// TODO: IF not proper item
		}
		testResult[0] = totalChange;
		return testResult;
	}

	public static Object[] parseContents(VendingMachineStoredContents contents) {
		// TODO: If not proper items
		int count = 0;
		for (List<PopCan> popRack : contents.unsoldPopCans) {
			for (PopCan pop : popRack) {
				count++;
			}
		}

		Object[] contentList = new Object[2 + count];

		int totalUnusedCoinsForChange = 0;
		for (List<Coin> coinRack : contents.unusedCoinsForChange) {
			for (Coin coin : coinRack) {
				totalUnusedCoinsForChange += coin.getValue();
			}
		}
		contentList[0] = totalUnusedCoinsForChange;

		int totalUnusedStorageBinCoins = 0;
		for (Coin storageBinCoin : contents.paymentCoinsInStorageBin) {
			totalUnusedStorageBinCoins += storageBinCoin.getValue();
		}
		contentList[1] = totalUnusedStorageBinCoins;

		int i = 2;
		for (List<PopCan> popRack : contents.unsoldPopCans) {
			for (PopCan popCan : popRack) {
				contentList[i++] = popCan.getName();
			}
		}
		return contentList;
	}

}
