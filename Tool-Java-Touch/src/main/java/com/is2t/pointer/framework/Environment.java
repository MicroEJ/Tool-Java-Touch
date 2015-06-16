/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.is2t.pointer.framework;

import ej.microui.EventGenerator;
import ej.microui.io.Display;
import ej.microui.io.ExplicitFlush;
import ej.microui.io.Pointer;

/**
 *
 */
public class Environment {

	public static final int ELEMENT_SIZE = 10;

	public final Display display;
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	public final ExplicitFlush g;
	public final Pointer pointer;

	/**
	 * Initializes environment.
	 */
	public Environment(Display display, int x, int y, int width, int height) {
		this(display, x, y, width, height, display.getNewExplicitFlush(), (Pointer) EventGenerator
				.get(Pointer.class, 0));
	}

	/**
	 * Initializes environment.
	 */
	public Environment(Display display, int x, int y, int width, int height, ExplicitFlush g, Pointer pointer) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.g = g;
		this.pointer = pointer;
	}

	public void fillScreen(int color) {
		this.g.setColor(color);
		this.g.fillRect(this.x, this.y, this.width, this.height);
	}

	public void drawPlus(int color, int x, int y) {
		this.g.setColor(color);
		this.g.drawHorizontalLine(x - ELEMENT_SIZE / 2, y, ELEMENT_SIZE);
		this.g.drawVerticalLine(x, y - ELEMENT_SIZE / 2, ELEMENT_SIZE);
	}

	public void drawCross(int color, int x, int y) {
		this.g.setColor(color);
		this.g.drawLine(x - ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x + ELEMENT_SIZE / 2, y + ELEMENT_SIZE / 2);
		this.g.drawLine(x + ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x - ELEMENT_SIZE / 2, y + ELEMENT_SIZE / 2);
	}

	public void drawCircle(int color, int x, int y) {
		this.g.setColor(color);
		this.g.drawCircle(x - ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, ELEMENT_SIZE);
	}

}
