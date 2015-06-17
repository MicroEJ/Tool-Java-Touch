/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.is2t.pointer.framework;

import ej.microui.Listener;

/**
 *
 */
public interface Test extends Listener {

	void start();

	void stop();

	String getTitle();

}
