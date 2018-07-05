/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;
import com.microej.test.test.TestThreshold;

import ej.microui.MicroUI;

/**
 * Entry point.
 */
public class Main {
	public static void main(String[] args) {
		MicroUI.start();

		TestManager testManager = new TestManager();
		AbstractTest test = new TestThreshold(testManager);
		test.show();
	}
}
