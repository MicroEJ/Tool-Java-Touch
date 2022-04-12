/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;
import com.microej.test.test.TestThreshold;

import ej.microui.MicroUI;
import ej.microui.display.Display;

/**
 * Entry point.
 */
public class Main {
	/**
	 * Set backlight to max for starters.
	 */
	public static final int MAX_BACKLIGHT = 100;

	/**
	 * Main method.
	 *
	 * @param args unused.
	 */
	public static void main(String[] args) {
		MicroUI.start();
		Display.getDisplay().setBacklight(MAX_BACKLIGHT);

		TestManager testManager = new TestManager();
		AbstractTest test = new TestThreshold(testManager);
		test.start();
	}
}
