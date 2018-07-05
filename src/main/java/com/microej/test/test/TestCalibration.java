/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;

import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

/**
 * Tests the dragging events distribution.
 */
public class TestCalibration extends AbstractTest {

	private int pressedX;
	private int pressedY;

	/**
	 * Initializes environment.
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
		int action = Pointer.getAction(event);
		switch (action) {
		case Pointer.PRESSED:
			int pointerX = pointer.getX();
			int pointerY = pointer.getY();
			this.pressedX = pointerX;
			this.pressedY = pointerY;
			repaint();
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
	public void paint(GraphicsContext g) {
		clearScreen(g);
		super.paint(g);

		// Draw title
		drawTitle(g, getTitle());

		g.setColor(Colors.SILVER);
		for (int x = 0; x < getInnerWidth(); x += 10) {
			g.drawVerticalLine(this.x + x , this.y, this.height);
		}
		for (int y = 0; y < getInnerHeight(); y += 10) {
			g.drawHorizontalLine(this.x , this.y + y, this.width);
		}
		g.setColor(Colors.BLACK);
		g.drawString(this.pressedX + "," + this.pressedY,
				this.x  + this.width / 2, this.y + this.height / 2,
				GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		drawCross(g, Colors.BLUE, this.pressedX, this.pressedY);
	}

	@Override
	public EventHandler getController() {
		return this;
	}
}
