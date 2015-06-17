/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.is2t.pointer.test;

import com.is2t.pointer.framework.AbstractTest;
import com.is2t.pointer.framework.Environment;

import ej.microui.Colors;
import ej.microui.io.Display;
import ej.microui.io.GraphicsContext;
import ej.microui.io.Pointer;

/**
 * Tests the dragging events distribution.
 */
public class TestThreshold extends AbstractTest {

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 30;

	private boolean pressed;
	private boolean dragged;
	private String message;

	public static void main(String[] args) {
		Display display = Display.getDefaultDisplay();
		Environment environment = new Environment(display, 0, 0, display.getWidth(), display.getHeight());
		TestThreshold test = new TestThreshold(environment);
		environment.pointer.setListener(test);
		test.start();
	}

	/**
	 * Initializes environment.
	 */
	public TestThreshold(Environment environment) {
		super(environment, "Threshold");
	}

	@Override
	public void performAction(int value) {
		// pointer event received
		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			int pointerX = this.environment.pointer.getX();
			int pointerY = this.environment.pointer.getY();
			this.message = "";
			if (accept(pointerX, pointerY)) {
				this.pressed = true;
				this.dragged = false;
				cleanScreen();
			} else {
				cleanScreen();
			}
			break;
		case Pointer.DRAGGED:
			if (this.pressed) {
				this.pressed = false;
				this.dragged = true;
				this.message = "NOK: Pressed then moved";
				cleanScreen();
			}
			break;
		case Pointer.RELEASED:
			if (this.pressed && !this.dragged) {
				this.message = "OK";
			}
			this.dragged = false;
			this.pressed = false;
			cleanScreen();
			break;
		}
	}

	private boolean accept(int x, int y) {
		int centerX = this.environment.x + this.environment.width / 2;
		int centerY = this.environment.y + this.environment.height / 2;
		int buttonX = centerX - BUTTON_WIDTH / 2;
		int buttonY = centerY - BUTTON_HEIGHT / 2;
		return (x > buttonX) && (x < buttonX + BUTTON_WIDTH) && (y > buttonY) && (y < buttonY + BUTTON_HEIGHT);
	}

	@Override
	public void start() {
		this.pressed = false;
		this.dragged = false;
		this.message = "";
		super.start();
	}

	@Override
	protected void cleanScreen(int color, boolean flush) {
		super.cleanScreen(color, false);

		int centerX = this.environment.x + this.environment.width / 2;
		int centerY = this.environment.y + this.environment.height / 2;
		int buttonX = centerX - BUTTON_WIDTH / 2;
		int buttonY = centerY - BUTTON_HEIGHT / 2;
		this.environment.g.setColor(Colors.BLACK);
		this.environment.g.drawRect(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		int buttonColor;
		if (this.pressed) {
			buttonColor = Colors.SILVER;
		} else if (this.dragged) {
			buttonColor = Colors.RED;
		} else {
			buttonColor = Colors.GRAY;
		}
		this.environment.g.setColor(buttonColor);
		this.environment.g.fillRect(buttonX + 1, buttonY + 1, BUTTON_WIDTH - 1, BUTTON_HEIGHT - 1);
		this.environment.g.setColor(Colors.BLACK);
		this.environment.g.drawString("Button", centerX, centerY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		this.environment.g.setColor(Colors.BLACK);
		this.environment.g.drawString(this.message, centerX, centerY - BUTTON_HEIGHT, GraphicsContext.HCENTER
				| GraphicsContext.VCENTER);
		if (flush) {
			this.environment.g.flush();
		}
	}

}
