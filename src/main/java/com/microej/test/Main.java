/*
 * Java
 *
 * Copyright 2017 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;
import com.microej.test.test.TestThreshold;

import ej.microui.MicroUI;

public class Main {
	public static void main(String[] args) {
		MicroUI.start();
		
		TestManager testManager = new TestManager();
		AbstractTest test = new TestThreshold(testManager);
		test.show();
	}
}
