/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.framework;

/**
 * Provides the minimal required implementation for a java touch test.
 */
public interface Test {

	/**
	 * Returns the title.
	 * @return the title.
	 */
	String getTitle();

	/**
	 * Starts the test.
	 */
	void start();

	/**
	 * Stops the test.
	 */
	void stop();
}
