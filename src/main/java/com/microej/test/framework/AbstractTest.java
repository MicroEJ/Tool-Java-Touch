/*
 * Java
 *
 * Copyright 2017 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
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
