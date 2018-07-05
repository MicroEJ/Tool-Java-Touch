/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.framework;

/**
 * Common implementation of a test.
 */
public abstract class AbstractTest extends AbstractDisplayable implements Test  {

	private final String name;

	/**
	 * Initializes environment.
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
		show();
	}

	@Override
	public void stop() {
		// Nothing to do;
	}
}
