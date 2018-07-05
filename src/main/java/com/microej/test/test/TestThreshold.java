/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;

import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

/**
 * Tests the dragging events distribution.
 */
public class TestThreshold extends AbstractTest {

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 30;

	private boolean pressed;
	private boolean dragged;
	private String message = "" ;


	/**
	 * Initializes environment.
	 */
	public TestThreshold(TestManager testManager) {
		super("Threshold", testManager);
	}

	@Override
	public boolean handleEvent(int value) {
		if(super.handleEvent(value)) {
			return true;
		}

		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			this.message = "";
			this.pressed = true;
			this.dragged = false;
			repaint();
			return true;
		case Pointer.DRAGGED:
			if (this.pressed) {
				this.pressed = false;
				this.dragged = true;
				this.message = "NOK: Pressed then moved";
				repaint();
			}
			return true;
		case Pointer.RELEASED:
			if (this.pressed && !this.dragged) {
				this.message = "OK";
			}
			this.dragged = false;
			this.pressed = false;
			repaint();
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		this.pressed = false;
		this.dragged = false;
		this.message = "";
		super.start();
	}

	@Override
	public void paint(GraphicsContext graphicsContext) {
		clearScreen(graphicsContext, Colors.WHITE);
		drawTitle(graphicsContext, getTitle());
		super.paint(graphicsContext);

		int centerX = this.x + this.width / 2;
		int centerY = this.y + this.height / 2;
		int buttonX = centerX - BUTTON_WIDTH / 2;
		int buttonY = centerY - BUTTON_HEIGHT / 2;
		graphicsContext.setColor(Colors.BLACK);
		graphicsContext.drawRect(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		int buttonColor;
		if (this.pressed) {
			buttonColor = Colors.SILVER;
		} else if (this.dragged) {
			buttonColor = Colors.RED;
		} else {
			buttonColor = Colors.GRAY;
		}
		graphicsContext.setColor(buttonColor);
		graphicsContext.fillRect(buttonX + 1, buttonY + 1, BUTTON_WIDTH - 1, BUTTON_HEIGHT - 1);
		graphicsContext.setColor(Colors.BLACK);
		graphicsContext.drawString("Button", centerX, centerY, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		graphicsContext.setColor(Colors.BLACK);
		graphicsContext.drawString(this.message, centerX, centerY - BUTTON_HEIGHT,
				GraphicsContext.HCENTER | GraphicsContext.VCENTER);
	}

	@Override
	public EventHandler getController() {
		return this;
	}
}