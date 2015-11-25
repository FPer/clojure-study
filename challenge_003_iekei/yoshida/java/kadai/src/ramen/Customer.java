package ramen;

public class Customer {

	private CustomerState currentState = CustomerState.WAIT;

	public CustomerState next() {
		this.currentState = this.currentState.next();
		return this.currentState;
	}

}
