/*
 * Java
 *
 * Copyright 2017 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
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
