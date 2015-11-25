package ramen2;

import java.util.ArrayList;
import java.util.List;

public class Store2 {

	public String visiting(String customers) {

		List<Integer> seats = new ArrayList<>();
		for (int i=0; i < 8; i++) {
			seats.add(0);
		}

		for (int i = 0; i < customers.length(); i++) {
			int customerNum = Character.getNumericValue(customers.charAt(i));

			this.next(seats);

			List<Integer> emptySeatIndex = new ArrayList<>();
			while((emptySeatIndex = this.getEmptySeatIndex(customerNum, seats)).isEmpty()) {
				this.next(seats);
			}

			this.sitDown(emptySeatIndex, seats);
		}

		return this.getSeatState(seats);
	}

	private void next(List<Integer> seats) {
		for (int i = 0; i < seats.size(); i++) {
			Integer seat = seats.get(i);

			if (seat == 3) {
				seats.set(i, 0);
			} else if (seat != 0) {
				seats.set(i, ++seat);
			}
		}
	}

	private String getSeatState(List<Integer> seats) {

		StringBuilder sb = new StringBuilder();

		for (Integer seat : seats) {
			if (seat == 0) {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}

		return sb.toString();
	}

	private void sitDown(List<Integer> emptySeatIndex, List<Integer> seats ) {

		for (int index: emptySeatIndex) {
			seats.set(index, 1);
		}
	}

	private List<Integer> getEmptySeatIndex(int customerNum, List<Integer> seats) {

		List<Integer> roundTable = new ArrayList<>();
		roundTable.addAll(seats);
		roundTable.addAll(seats);

		List<Integer> emptySeatIndex = new ArrayList<>();

		for (int i = 0; i < roundTable.size(); i++) {
			Integer seat = roundTable.get(i);
			if (seat == 0) {
				emptySeatIndex.add(i % 8);
				if (emptySeatIndex.size() == customerNum) {
					return emptySeatIndex;
				}
			} else {
				emptySeatIndex.clear();
			}
		}

		emptySeatIndex.clear();
		return emptySeatIndex;
	}
}
