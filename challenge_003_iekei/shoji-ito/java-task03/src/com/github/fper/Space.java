package com.github.fper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Space {
	private static final BigDecimal MAX_X = new BigDecimal(8);
	private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(3);

	private List<Box> boxs = new ArrayList<Box>();

	public void addBox(BigDecimal width) {
		List<BigDecimal> dxList = new ArrayList<BigDecimal>();
		for (int i = 0; i <= MAX_X.subtract(width).intValue(); i++) {
			dxList.add(new BigDecimal(i));
		}
		for (int i = -1; i >= MAX_X.multiply(new BigDecimal(-1)).intValue(); i--) {
			dxList.add(new BigDecimal(i));
		}

		BigDecimal x = BigDecimal.ZERO;
		BigDecimal y = getMaxY();
		if (boxs.size() > 0) {
			y = y.add(BigDecimal.ONE);
		}

		while (Boolean.TRUE) {
			for (BigDecimal dx : dxList) {
				Box box = new Box(dx, y, width, DEFAULT_HEIGHT);
				if (!box.isConflict(boxs)) {
					x = dx;
					break;
				}
			}

			Box box = new Box(x, y, width, DEFAULT_HEIGHT);
			if (!box.isConflict(boxs)) {
				// System.out.println("(x, y, w)=(" + x + "," + y + "," + width + ")");
				boxs.add(box);
				boxs.add(new Box(x.compareTo(BigDecimal.ZERO) >= 0 ? x.subtract(MAX_X) : x.add(MAX_X), y, width, DEFAULT_HEIGHT));
				break;
			}
			y = y.add(BigDecimal.ONE);
		}
	}

	public void addBoxAll(List<BigDecimal> widthList) {
		for (BigDecimal width : widthList) {
			addBox(width);
		}
	}

	public List<BigDecimal> getResult() {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		BigDecimal y = getMaxY();
		for (int i = 0; i < MAX_X.intValue(); i++) {
			BigDecimal v = BigDecimal.ZERO;
			Box box = new Box(new BigDecimal(i), y, BigDecimal.ONE, BigDecimal.ONE);
			if (box.isConflict(boxs)) {
				v = BigDecimal.ONE;
			}
			result.add(v);
		}
		return result;
	}

	private BigDecimal getMaxY() {
		BigDecimal maxY = BigDecimal.ZERO;
		for (Box box : boxs) {
			if (maxY.compareTo(box.getY()) < 0) {
				maxY = box.getY();
			}
		}
		return maxY;
	}

}
