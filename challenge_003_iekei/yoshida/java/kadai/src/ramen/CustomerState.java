package ramen;

public enum CustomerState {

	// 調理待ち
	WAIT {
		@Override
		public CustomerState next() {
			return EATING;
		}

	},

	// 食事中
	EATING {
		@Override
		public CustomerState next() {
			return BREAK;
		}

	},

	// 一休み
	BREAK;

	public CustomerState next() {
		return null;
	}
}
