package ramen;

public class Seat {

	private Customer customer = null;

	public void next() {
		if (this.customer != null) {
			if (this.customer.next() == null) {
				this.leave();
			}
		}
	}

	public boolean isEmpty() {
		return customer == null;
	}

	public void sitDown(Customer customer) {
		this.customer = customer;
	}

	private void leave() {
		this.customer = null;
	}
}
