package ramen2;

import static org.junit.Assert.*;

import org.junit.Test;

public class Store2Test {

	@Test
	public void testVisiting() {

		assertEquals("11111110", new Store2().visiting("3316"));
		assertEquals("11111111", new Store2().visiting("3342153"));
		assertEquals("11111111", new Store2().visiting("8"));
		assertEquals("11110110", new Store2().visiting("232624"));
		assertEquals("10000000", new Store2().visiting("1"));
		assertEquals("11111000", new Store2().visiting("224673432"));
		assertEquals("11111110", new Store2().visiting("123464334"));
		assertEquals("11111111", new Store2().visiting("44372332"));
		assertEquals("11111111", new Store2().visiting("2344"));
		assertEquals("11111100", new Store2().visiting("6448476233"));
		assertEquals("11111111", new Store2().visiting("4345174644"));
		assertEquals("11111110", new Store2().visiting("77743"));
		assertEquals("10000000", new Store2().visiting("2136524281"));
		assertEquals("11100000", new Store2().visiting("344373"));
		assertEquals("11111111", new Store2().visiting("434226"));
		assertEquals("11111110", new Store2().visiting("7433223"));
		assertEquals("11110111", new Store2().visiting("2246534"));
		assertEquals("11111110", new Store2().visiting("634"));
		assertEquals("11111100", new Store2().visiting("2222"));
		assertEquals("11111111", new Store2().visiting("2442343238"));
		assertEquals("11111111", new Store2().visiting("7243344"));
		assertEquals("10111111", new Store2().visiting("26147234"));
		assertEquals("10011111", new Store2().visiting("34424"));
		assertEquals("11011111", new Store2().visiting("6334"));
		assertEquals("11011110", new Store2().visiting("3828342"));
		assertEquals("11110000", new Store2().visiting("4431"));
		assertEquals("11111111", new Store2().visiting("2843212125"));
		assertEquals("11000000", new Store2().visiting("333336482"));
		assertEquals("11110000", new Store2().visiting("374"));
		assertEquals("11100111", new Store2().visiting("4382333"));
	}

}
