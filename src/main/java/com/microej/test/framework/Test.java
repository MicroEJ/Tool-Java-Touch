/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.framework;

/**
 *
 */
public interface Test {

	/**
	 * Returns the title.
	 * @return the title.
	 */
	String getTitle();

	void start();

	void stop();
}
