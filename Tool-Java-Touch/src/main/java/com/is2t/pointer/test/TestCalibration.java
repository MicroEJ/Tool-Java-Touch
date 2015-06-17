/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.is2t.pointer.test;

import com.is2t.pointer.framework.AbstractTest;
import com.is2t.pointer.framework.Environment;

import ej.microui.Colors;
import ej.microui.io.Display;
import ej.microui.io.GraphicsContext;
import ej.microui.io.Pointer;

/**
 * Tests the dragging events distribution.
 */
public class TestCalibration extends AbstractTest {

	private int pressedX;
	private int pressedY;

	public static void main(String[] args) {
		Display display = Display.getDefaultDisplay();
		Environment environment = new Environment(display, 0, 0, display.getWidth(), display.getHeight());
		TestCalibration test = new TestCalibration(environment);
		environment.pointer.setListener(test);
		test.start();
	}

	/**
	 * Initializes environment.
	 */
	public TestCalibration(Environment environment) {
		super(environment, "Calibration");
	}

	@Override
	public void performAction(int value) {
		// pointer event received
		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			int pointerX = this.environment.pointer.getX();
			int pointerY = this.environment.pointer.getY();
			this.pressedX = pointerX;
			this.pressedY = pointerY;
			cleanScreen();
			break;
		}
	}

	@Override
	public void start() {
		this.pressedX = -Environment.ELEMENT_SIZE;
		this.pressedY = -Environment.ELEMENT_SIZE;
		super.start();
	}

	@Override
	protected void cleanScreen(int color, boolean flush) {
		super.cleanScreen(color, false);
		this.environment.g.setColor(Colors.SILVER);
		for (int x = 0; x < this.environment.width; x += 10) {
			this.environment.g.drawVerticalLine(this.environment.x + x, this.environment.y, this.environment.height);
		}
		for (int y = 0; y < this.environment.height; y += 10) {
			this.environment.g.drawHorizontalLine(this.environment.x, this.environment.y + y, this.environment.width);
		}
		this.environment.g.setColor(Colors.BLACK);
		this.environment.g.drawString(this.pressedX + "," + this.pressedY, this.environment.x + this.environment.width
				/ 2, this.environment.y + this.environment.height / 2, GraphicsContext.HCENTER
				| GraphicsContext.VCENTER);
		this.environment.drawCross(Colors.BLUE, this.pressedX, this.pressedY);
		if (flush) {
			this.environment.g.flush();
		}
	}

}
