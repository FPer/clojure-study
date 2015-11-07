package com.github.fper;

import java.math.BigDecimal;
import java.util.List;

public class Box {
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal width;
	private BigDecimal height;

	public Box(BigDecimal x, BigDecimal y, BigDecimal width, BigDecimal height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getCenterX() {
		return x.add(width.divide(new BigDecimal(2)));
	}

	public BigDecimal getCenterY() {
		return y.add(height.divide(new BigDecimal(2)));
	}

	public Boolean isConflict(Box box) {
		BigDecimal intervalX = this.getCenterX().subtract(box.getCenterX()).abs();
		BigDecimal averageWidth = this.width.add(box.getWidth()).divide(new BigDecimal(2));

		BigDecimal intervalY = this.getCenterY().subtract(box.getCenterY()).abs();
		BigDecimal averageHeight = this.height.add(box.getHeight()).divide(new BigDecimal(2));

		if (intervalX.compareTo(averageWidth) < 0 && intervalY.compareTo(averageHeight) < 0) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	public Boolean isConflict(List<Box> boxs) {
		for (Box box : boxs) {
			if (this.isConflict(box)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

}
