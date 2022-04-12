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
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Tests the dragging events distribution.
 */
public class TestDrag extends AbstractTest {

	private int pointerX;
	private int pointerY;

	private boolean clearScreen = true;

	/**
	 * Initializes environment.
	 *
	 * @param testManager the provided testManager
	 */
	public TestDrag(TestManager testManager) {
		super("Drag", testManager);
	}

	@Override
	public boolean handleEvent(int event) {

		if(super.handleEvent(event)) {
			return true;
		}

		Pointer pointer = (Pointer) Event.getGenerator(event);
		pointerX = pointer.getX();
		pointerY = pointer.getY();
		int action = Buttons.getAction(event);
		if ((action == Buttons.PRESSED) || (action == Pointer.DRAGGED) || (action == Buttons.RELEASED)) {
			requestRender();
			return true;
		} else if (action == Buttons.DOUBLE_CLICKED) {
			clearScreen = true;
			requestRender();
			return true;
		}
		return false;
	}

	@Override
	public void render(GraphicsContext g) {
		if(clearScreen) {
			super.clearScreen(g);
			super.render(g);
			drawTitle(g, getTitle());
			clearScreen = false;
		}
		drawCross(g, Colors.RED, pointerX, pointerY);
	}

	@Override
	public void start() {
		this.clearScreen = true;
		super.start();
	}
}