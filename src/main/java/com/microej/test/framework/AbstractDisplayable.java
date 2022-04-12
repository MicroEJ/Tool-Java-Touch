/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.framework;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.EventGenerator;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Represents a displayable test. Contains all generic methods needed.
 */
public abstract class AbstractDisplayable extends Displayable {

	private static final int SIDE_BAR_WIDTH = 20;
	private static final int TITLE_UPPER_PADDING = 10;
	private static final int TITLE_CORNER_PADDING = 5;

	/**
	 * Size (length) of one bar of the cross needed in some of the tests.
	 */
	protected static final int ELEMENT_SIZE = 10;

	private final TestManager testManager;
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * The mode of the display. True if landscape, false otherwise (which means
	 * portrait).
	 */
	private final boolean landScape;

	/**
	 * Constructor. Includes the testManager to navigate to the next or previous
	 * test.
	 *
	 * @param testManager the testManager.
	 */
	public AbstractDisplayable(TestManager testManager) {
		this.testManager = testManager;

		int width = Display.getDisplay().getWidth();
		int height = Display.getDisplay().getHeight();

		this.landScape = width > height;

		if (landScape) {
			this.x = SIDE_BAR_WIDTH;
			this.y = 0;
			this.width = width - (SIDE_BAR_WIDTH * 2);
			this.height = height;
		} else {
			this.x = 0;
			this.y = SIDE_BAR_WIDTH;
			this.width = width;
			this.height = height - (SIDE_BAR_WIDTH * 2);
		}
	}

	/**
	 * Returns true if is landscape.
	 * @return true if is landscape.
	 */
	protected boolean isLandScape() {
		return this.landScape;
	}

	@Override
	public void render(GraphicsContext g) {
		int width = Display.getDisplay().getWidth();
		int height = Display.getDisplay().getHeight();

		if (!this.landScape) {
			int middleX = width / 2;

			g.setColor(Colors.GRAY);
			Painter.fillRectangle(g, 0, 0, width, SIDE_BAR_WIDTH);
			Painter.fillRectangle(g, 0, height - SIDE_BAR_WIDTH, width, height);

			g.setColor(Colors.BLACK);
			int leftUpperNavStartX = middleX - SIDE_BAR_WIDTH;
			int leftUpperNavStartY = SIDE_BAR_WIDTH - 1;
			int rightUpperNavStartX = middleX + SIDE_BAR_WIDTH;
			int rightUpperNavStartY = SIDE_BAR_WIDTH - 1;
			Painter.drawLine(g, leftUpperNavStartX, leftUpperNavStartY, middleX, 0); // Left upper arrow part
			Painter.drawLine(g, rightUpperNavStartX, rightUpperNavStartY, middleX, 0); // Right upper arrow part

			int leftLowerNavStartX = middleX - SIDE_BAR_WIDTH;
			int leftLowerNavStartY = height - SIDE_BAR_WIDTH;
			int rightLowerNavStartX = middleX + SIDE_BAR_WIDTH;
			int rightLowerNavStartY = height - SIDE_BAR_WIDTH;
			Painter.drawLine(g, leftLowerNavStartX, leftLowerNavStartY, middleX, height); // L. lower arrow part
			Painter.drawLine(g, rightLowerNavStartX, rightLowerNavStartY, middleX, height); // R. lower arrow part

		} else {
			int middleY = height / 2;

			g.setColor(Colors.GRAY);
			Painter.fillRectangle(g, 0, 0, SIDE_BAR_WIDTH, height);
			Painter.fillRectangle(g, width - SIDE_BAR_WIDTH, 0, width, height);

			g.setColor(Colors.BLACK);

			int lowerLeftNavEndX = SIDE_BAR_WIDTH - 1;
			int upperLeftNavEndX = SIDE_BAR_WIDTH - 1;
			int upperLeftNavEndY = middleY + SIDE_BAR_WIDTH - 1;
			int lowerLeftNavEndY = middleY - SIDE_BAR_WIDTH + 1;
			Painter.drawLine(g, 0, middleY, lowerLeftNavEndX, lowerLeftNavEndY); // Lower arrow part
			Painter.drawLine(g, 0, middleY, upperLeftNavEndX, upperLeftNavEndY); // Upper arrow part

			int upperRightNavEndX = width - SIDE_BAR_WIDTH;
			int upperRightNavEndY = middleY - SIDE_BAR_WIDTH;
			int lowerRightNavEndX = width - SIDE_BAR_WIDTH;
			int lowerRightNavEndY = middleY + SIDE_BAR_WIDTH - 1;
			Painter.drawLine(g, width, middleY, upperRightNavEndX, upperRightNavEndY); // Upper arrow part
			Painter.drawLine(g, width, middleY - 1, lowerRightNavEndX, lowerRightNavEndY); // Lower arrow part
		}
	}

	/**
	 * Returns the x position.
	 * @return the x position
	 */
	protected int getX() {
		return this.x;
	}

	/**
	 * Returns the y position.
	 * @return the y position
	 */
	protected int getY() {
		return this.y;
	}

	/**
	 * Returns the with of the inner screen.
	 * @return the with of the inner screen.
	 */
	protected int getInnerWidth() {
		return this.width;
	}

	/**
	 * Returns the height of the inner screen.
	 * @return the height of the inner screen
	 */
	protected int getInnerHeight() {
		return this.height;
	}

	/**
	 * Draws a cross at at <code>(x,y)</code> using the specific color.
	 *
	 * @param graphicsContext
	 *            GraphicsContext for drawing
	 * @param color
	 *            the color of the cross
	 * @param x
	 *            the x coordinate of the cross.
	 * @param y
	 *            the y coordinate of the cross.
	 */
	protected void drawCross(GraphicsContext graphicsContext, int color, int x, int y) {
		graphicsContext.setColor(color);
		Painter.drawLine(graphicsContext, x - ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x + ELEMENT_SIZE / 2,
				y + ELEMENT_SIZE / 2);
		Painter.drawLine(graphicsContext, x + ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x - ELEMENT_SIZE / 2,
				y + ELEMENT_SIZE / 2);
	}

	/**
	 * Clears the screen with default color (White).
	 *
	 * @see clearScreen(GraphicsContext, int);
	 *
	 * @param g GraphicsContext for drawing
	 */
	protected void clearScreen(GraphicsContext g) {
		this.clearScreen(g, Colors.WHITE);
	}

	/**
	 * Clears the screen.
	 *
	 * @param graphicsContext GraphicsContext for drawing
	 * @param rgbColor        new color of background
	 */
	protected void clearScreen(GraphicsContext graphicsContext, int rgbColor) {
		graphicsContext.setColor(rgbColor);
		Painter.fillRectangle(graphicsContext, 0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Pointer.EVENT_TYPE) {
			Pointer pointer = (Pointer) EventGenerator.get(Event.getGeneratorId(event));
			int pointerX = pointer.getX();
			int pointerY = pointer.getY();
			if (Buttons.isReleased(event)) {
				if (landScape) {
					if (pointerX < SIDE_BAR_WIDTH) {
						testManager.showPreviousTest();
						return true;
					} else if (pointerX > width + SIDE_BAR_WIDTH) {
						testManager.showNextTest();
						return true;
					}
				} else {
					if ((pointerY < SIDE_BAR_WIDTH)) {
						testManager.showPreviousTest();
						return true;
					} else if (pointerY > height - SIDE_BAR_WIDTH) {
						testManager.showNextTest();
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Draws the title at the top of display.
	 *
	 * @param graphicsContext GraphicsContext for drawing
	 * @param title           String to draw
	 */
	protected void drawTitle(GraphicsContext graphicsContext, String title) {
		graphicsContext.setColor(Colors.BLACK);
		if (isLandScape()) {
			Painter.drawString(graphicsContext, title, Font.getDefaultFont(), width / 2, TITLE_UPPER_PADDING);
		} else { // e. g. Portrait
			Painter.drawString(graphicsContext, title, Font.getDefaultFont(), getX() + TITLE_CORNER_PADDING,
					TITLE_CORNER_PADDING);
		}
	}
}