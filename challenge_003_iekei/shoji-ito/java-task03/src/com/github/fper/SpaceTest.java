package com.github.fper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SpaceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");

		test1(Arrays.asList(3));
		test1(Arrays.asList(3, 4));
		test1(Arrays.asList(3, 4, 2));
		test1(Arrays.asList(3, 2, 4));
	}

	private void test1(List<Integer> iList) {
		List<BigDecimal> bList = new ArrayList<BigDecimal>();
		for (Integer i : iList) {
			bList.add(new BigDecimal(i));
		}
		test2(bList);
	}

	private void test2(List<BigDecimal> widthList) {
		Space space = new Space();
		space.addBoxAll(widthList);
		space.getResult();
		List<BigDecimal> result = space.getResult();
		for (BigDecimal w : widthList) {
			System.out.print(w);
		}
		System.out.print("\n->");
		for (BigDecimal v : result) {
			System.out.print(v);
		}
		System.out.print("\n");
	}

}
