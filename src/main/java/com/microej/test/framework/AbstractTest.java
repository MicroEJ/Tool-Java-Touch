/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.framework;

import ej.microui.display.Display;

/**
 * Common implementation of a test.
 */
public abstract class AbstractTest extends AbstractDisplayable implements Test  {

	private final String name;

	/**
	 * Initializes environment.
	 *
	 * @param name        the name of the test
	 * @param testManager the provided testManager
	 */
	public AbstractTest(String name, TestManager testManager) {
		super(testManager);
		this.name = name;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public void start() {
		Display.getDisplay().requestShow(this);
	}

	@Override
	public void stop() {
		// Nothing to do.
	}
}
