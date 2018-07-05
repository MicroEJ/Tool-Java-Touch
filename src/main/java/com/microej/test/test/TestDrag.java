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
public class TestDrag extends AbstractTest {

	private int pointerX;
	private int pointerY;

	private boolean clearScreen = true;
	/**
	 * Initializes environment.
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
		int action = Pointer.getAction(event);
		switch (action) {
		case Pointer.PRESSED:
			repaint();
			return true;
		case Pointer.DRAGGED:
			repaint();
			return true;
		case Pointer.RELEASED:
			repaint();
			return true;
		case Pointer.DOUBLE_CLICKED:
			clearScreen = true;
			repaint();
			return true;
		}
		return false;
	}

	@Override
	public void paint(GraphicsContext g) {
		if(clearScreen) {
			super.clearScreen(g);
			super.paint(g);
			drawTitle(g, getTitle());
			clearScreen = false;
		}
		drawCross(g, Colors.RED, pointerX, pointerY);
	}

	@Override
	public EventHandler getController() {
		return this;
	}

	@Override
	public void start() {
		this.clearScreen = true;
		super.start();
	}
}