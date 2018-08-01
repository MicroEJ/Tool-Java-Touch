/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.framework;

import com.microej.test.test.TestCalibration;
import com.microej.test.test.TestDrag;
import com.microej.test.test.TestFiltering;
import com.microej.test.test.TestTarget;
import com.microej.test.test.TestThreshold;

public class TestManager {

	private final AbstractTest[] tests;
	private int currentTest;

	public TestManager()
	{
		this.tests = new AbstractTest[] { new TestThreshold(this),
				new TestFiltering(this),
				new TestCalibration(this),
				new TestDrag(this),
				new TestTarget(this)
		};
		currentTest = 0;
	}

	/**
	 * Shows the previous test at the screen.
	 */
	public void showPreviousTest() {
		this.currentTest = (--this.currentTest) % this.tests.length;
		this.tests[Math.abs(this.currentTest)].start();
	}

	/**
	 * Shows the new test at the screen.
	 */
	public void showNextTest() {
		this.currentTest = (++this.currentTest) % this.tests.length;
		this.tests[this.currentTest].start();
	}
}