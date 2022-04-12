/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.framework;

import com.microej.test.test.TestCalibration;
import com.microej.test.test.TestDrag;
import com.microej.test.test.TestFiltering;
import com.microej.test.test.TestTarget;
import com.microej.test.test.TestThreshold;

/**
 * Moves to the next or previous test based on user input.
 */
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
	 *
	 * The previous from the first one is the last one.
	 */
	public void showPreviousTest() {
		--this.currentTest;
		if (this.currentTest < 0) {
			this.currentTest = this.currentTest + this.tests.length;
		}
		this.tests[this.currentTest].start();
	}

	/**
	 * Shows the next test at the screen.
	 *
	 * The next from the last one is the first one.
	 */
	public void showNextTest() {
		++this.currentTest;
		this.currentTest = this.currentTest % this.tests.length;
		this.tests[this.currentTest].start();
	}
}