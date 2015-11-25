package ramen;

import java.util.ArrayList;
import java.util.List;

public class Store {

	private List<Seat> seats = new ArrayList<>();
	private List<Seat> roundTable = new ArrayList<>();

	public Store() {

		for (int i = 0; i < 8; i++) {
			this.seats.add(new Seat());
		}

		this.roundTable.addAll(seats);
		this.roundTable.addAll(seats);
	}

	public String visiting(String customers) {

		for (int i = 0; i < customers.length(); i++) {
			int customerNum = Character.getNumericValue(customers.charAt(i));

			this.next();

			List<Integer> emptySeatIndex = new ArrayList<>();
			while((emptySeatIndex = this.getEmptySeatIndex(customerNum)).isEmpty()) {
				this.next();
			}

			this.sitDown(emptySeatIndex);
		}

		return this.getSeatState();
	}

	private void next() {
		for (Seat seat : this.seats) {
			seat.next();
		}
	}

	private String getSeatState() {

		StringBuilder sb = new StringBuilder();

		for (Seat seat : this.seats) {
			if (seat.isEmpty()) {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}

		return sb.toString();
	}

	private void sitDown(List<Integer> emptySeatIndex) {

		for (int index: emptySeatIndex) {
			this.seats.get(index).sitDown(new Customer());
		}
	}

	private List<Integer> getEmptySeatIndex(int customerNum) {

		List<Integer> emptySeatIndex = new ArrayList<>();

		for (int i = 0; i < this.roundTable.size(); i++) {
			Seat seat = this.roundTable.get(i);
			if (seat.isEmpty()) {
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
