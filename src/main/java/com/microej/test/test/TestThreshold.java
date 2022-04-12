/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;

import ej.microui.display.Colors;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Tests the dragging sensitivity.
 */
public class TestThreshold extends AbstractTest {

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 30;
	private static final int MESSAGE_PADDING = 4; // To center the message
	private static final String EMPTY_STRING = "";
	private static final int TEXT_POSITION_SHRINK_FACTOR = 6;

	private boolean pressed;
	private boolean dragged;
	private String message = EMPTY_STRING;

	/**
	 * Initializes environment.
	 *
	 * @param testManager the provided testManager
	 */
	public TestThreshold(TestManager testManager) {
		super("Threshold", testManager);
	}

	@Override
	public boolean handleEvent(int value) {
		if(super.handleEvent(value)) {
			return true;
		}

		int action = Buttons.getAction(value);
		switch (action) {
		case Buttons.PRESSED:
			this.message = EMPTY_STRING;
			this.pressed = true;
			this.dragged = false;
			requestRender();
			return true;
		case Pointer.DRAGGED:
			if (this.pressed) {
				this.pressed = false;
				this.dragged = true;
				this.message = "NOK: Pressed then moved";
				requestRender();
			}
			return true;
		case Buttons.RELEASED:
			if (this.pressed && !this.dragged) {
				this.message = "OK";
			}
			this.dragged = false;
			this.pressed = false;
			requestRender();
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		this.pressed = false;
		this.dragged = false;
		this.message = EMPTY_STRING;
		super.start();
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		clearScreen(graphicsContext, Colors.WHITE);
		drawTitle(graphicsContext, getTitle());
		super.render(graphicsContext);

		int centerX = getX() + getInnerWidth() / 2;
		int centerY = getY() + getInnerHeight() / 2;
		int buttonX = centerX - BUTTON_WIDTH / 2;
		int buttonY = centerY - BUTTON_HEIGHT / 2;
		graphicsContext.setColor(Colors.BLACK);
		Painter.drawRectangle(graphicsContext, buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		int buttonColor;
		if (this.pressed) {
			buttonColor = Colors.SILVER;
		} else if (this.dragged) {
			buttonColor = Colors.RED;
		} else {
			buttonColor = Colors.GRAY;
		}
		graphicsContext.setColor(buttonColor);
		Painter.fillRectangle(graphicsContext, buttonX + 1, buttonY + 1, BUTTON_WIDTH - 1, BUTTON_HEIGHT - 1);

		int buttonTextPosX = centerX - BUTTON_WIDTH / TEXT_POSITION_SHRINK_FACTOR;
		int buttonTextPosY = centerY - BUTTON_HEIGHT / TEXT_POSITION_SHRINK_FACTOR;
		graphicsContext.setColor(Colors.BLACK);
		Painter.drawString(graphicsContext, "Button", Font.getDefaultFont(), buttonTextPosX, buttonTextPosY);
		graphicsContext.setColor(Colors.BLACK);
		Painter.drawString(graphicsContext, this.message, Font.getDefaultFont(),
				centerX - MESSAGE_PADDING - this.message.length() * 2,
				centerY - BUTTON_HEIGHT);
	}
}