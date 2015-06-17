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
import ej.microui.io.Pointer;

/**
 * Tests the dragging events distribution.
 */
public class TestDrag extends AbstractTest {

	public static void main(String[] args) {
		Display display = Display.getDefaultDisplay();
		Environment environment = new Environment(display, 0, 0, display.getWidth(), display.getHeight());
		TestDrag test = new TestDrag(environment);
		environment.pointer.setListener(test);
		test.start();
	}

	/**
	 * Initializes environment.
	 */
	public TestDrag(Environment environment) {
		super(environment, "Drag");
	}

	@Override
	public void performAction(int value) {
		// pointer event received
		int pointerX = this.environment.pointer.getX();
		int pointerY = this.environment.pointer.getY();
		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			this.environment.drawCircle(Colors.BLUE, pointerX, pointerY);
			this.environment.g.flush();
			break;
		case Pointer.DRAGGED:
			this.environment.drawPlus(Colors.RED, pointerX, pointerY);
			this.environment.g.flush();
			break;
		case Pointer.RELEASED:
			this.environment.drawCross(Colors.GREEN, pointerX, pointerY);
			this.environment.g.flush();
			break;
		case Pointer.DOUBLE_CLICKED:
			cleanScreen();
			break;
		}
	}

}
