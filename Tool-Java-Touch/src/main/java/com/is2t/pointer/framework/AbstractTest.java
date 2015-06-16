/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.is2t.pointer.framework;

import ej.microui.Colors;
import ej.microui.io.GraphicsContext;

/**
 * Common implementation of a test.
 */
public abstract class AbstractTest implements Test {

	protected final Environment environment;
	private final String name;

	/**
	 * Initializes environment.
	 */
	public AbstractTest(Environment environment, String name) {
		this.environment = environment;
		this.name = name;
	}
	
	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public void start() {
		cleanScreen(true);
	}

	@Override
	public void stop() {
	}

	protected void cleanScreen() {
		cleanScreen(Colors.WHITE, true);
	}

	protected void cleanScreen(boolean flush) {
		cleanScreen(Colors.WHITE, flush);
	}

	protected void cleanScreen(int color) {
		cleanScreen(color, true);
	}

	protected void cleanScreen(int color, boolean flush) {
		this.environment.fillScreen(color);
		this.environment.g.setColor(Colors.BLACK);
		this.environment.g.drawString(getTitle(), this.environment.x + this.environment.width / 2,
				this.environment.y + 5, GraphicsContext.HCENTER | GraphicsContext.TOP);
		if (flush) {
			this.environment.g.flush();
		}
	}

	@Override
	public void performAction() {
	}

	@Override
	public void performAction(int value, Object object) {
	}

}
