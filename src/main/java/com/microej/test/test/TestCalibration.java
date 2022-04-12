/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;

import ej.microui.display.Colors;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Tests calibration.
 */
public class TestCalibration extends AbstractTest {

	private static final int MAX_NUM_LINES = 10;
	private int pressedX;
	private int pressedY;

	/**
	 * Initializes environment.
	 *
	 * @param testManager the provided testManager
	 */
	public TestCalibration(TestManager testManager) {
		super("Calibration", testManager);
	}

	@Override
	public boolean handleEvent(int event) {

		if(super.handleEvent(event)) {
			return true;
		}

		Pointer pointer = (Pointer) Event.getGenerator(event);
		int action = Buttons.getAction(event);
		if (action == Buttons.PRESSED) {
			int pointerX = pointer.getX();
			int pointerY = pointer.getY();
			this.pressedX = pointerX;
			this.pressedY = pointerY;
			requestRender();
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		this.pressedX = -ELEMENT_SIZE;
		this.pressedY = -ELEMENT_SIZE;
		super.start();
	}

	@Override
	public void render(GraphicsContext g) {
		clearScreen(g);
		super.render(g);

		// Draw lines
		g.setColor(Colors.SILVER);
		for (int x = 0; x < getInnerWidth(); x += MAX_NUM_LINES) {
			Painter.drawVerticalLine(g, getX() + x, getY(), getInnerHeight());
		}
		for (int y = 0; y < getInnerHeight(); y += MAX_NUM_LINES) {
			Painter.drawHorizontalLine(g, getX(), getY() + y, getInnerWidth());
		}
		g.setColor(Colors.BLACK);

		// Draw title
		drawTitle(g, getTitle());

		Painter.drawString(g, this.pressedX + "," + this.pressedY, Font.getDefaultFont(), getInnerWidth() / 2,
				getInnerHeight() / 2);
		drawCross(g, Colors.BLUE, this.pressedX, this.pressedY);
	}
}
