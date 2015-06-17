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
public class TestFiltering extends AbstractTest {

	public static void main(String[] args) {
		Display display = Display.getDefaultDisplay();
		Environment environment = new Environment(display, 0, 0, display.getWidth(), display.getHeight());
		TestFiltering test = new TestFiltering(environment);
		environment.pointer.setListener(test);
		test.start();
	}

	/**
	 * Initializes environment.
	 */
	public TestFiltering(Environment environment) {
		super(environment, "Filtering");
	}

	@Override
	public void performAction(int value) {
		// pointer event received
		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			break;
		case Pointer.DRAGGED:
			blink();
			break;
		case Pointer.RELEASED:
			break;
		}
	}

	private void blink() {
		cleanScreen(Colors.RED);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		cleanScreen(Colors.WHITE);
	}

}
