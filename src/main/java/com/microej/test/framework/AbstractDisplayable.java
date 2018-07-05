/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.framework;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.event.EventGenerator;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

public abstract class AbstractDisplayable extends Displayable implements EventHandler {

	public static final int ELEMENT_SIZE = 10;

	protected TestManager testManager;
	protected int x, y;
	protected int width, height;
	protected boolean landScape;

	private final int padding = 20;

	public AbstractDisplayable(TestManager testManager) {
		super(Display.getDefaultDisplay());
		this.testManager = testManager;

		int width = getDisplay().getWidth();
		int height = getDisplay().getHeight();

		this.landScape = width > height;

		if (landScape) {
			this.x = padding;
			this.y = 0;
			this.width = width - (padding * 2);
			this.height = height;
		} else {
			this.x = 0;
			this.y = padding;
			this.width = width;
			this.height = height - (padding * 2);
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
	public void paint(GraphicsContext g) {
		int width = getDisplay().getWidth();
		int height = getDisplay().getHeight();

		if (!this.landScape) {
			int middle_x = width / 2;

			g.setColor(Colors.GRAY);
			g.fillRect(0, 0, width, padding);
			g.fillRect(0, height-padding, width, height);


			g.setColor(Colors.BLACK);
			g.drawLine(middle_x - padding, padding,  middle_x, 0);
			g.drawLine(middle_x + padding, padding,  middle_x, 0);
			g.drawLine(middle_x - padding, height-padding,  middle_x, height);
			g.drawLine(middle_x + padding, height-padding,  middle_x, height);

		} else {
			int middle_y = height / 2;

			g.setColor(Colors.GRAY);
			g.fillRect(0, 0, padding, height);
			g.fillRect(width-padding, 0, width, height);

			g.setColor(Colors.BLACK);
			g.drawLine(0, middle_y, padding - 1, middle_y - padding);
			g.drawLine(0, middle_y, padding - 1, middle_y + padding);
			g.drawLine(width, middle_y, width - padding, middle_y - padding);
			g.drawLine(width, middle_y, width - padding, middle_y + padding);
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
		graphicsContext.drawLine(x - ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x + ELEMENT_SIZE / 2, y + ELEMENT_SIZE / 2);
		graphicsContext.drawLine(x + ELEMENT_SIZE / 2, y - ELEMENT_SIZE / 2, x - ELEMENT_SIZE / 2, y + ELEMENT_SIZE / 2);
	}

	/**
	 * Clear the screen with default color (White) @see
	 * clearScreen(GraphicsContext, int);
	 * 
	 * @param g
	 *            GraphicsContext for drawing
	 */
	protected void clearScreen(GraphicsContext g) {
		this.clearScreen(g, Colors.WHITE);
	}

	/**
	 * Clear the screen.
	 * @param graphicsContext GraphicsContext for drawing
	 * @param rgbColor new color of background
	 */
	protected void clearScreen(GraphicsContext graphicsContext, int rgbColor) {
		graphicsContext.setColor(rgbColor);
		graphicsContext.fillRect(0, 0, graphicsContext.getDisplay().getWidth(), graphicsContext.getDisplay().getHeight());
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER) {
			Pointer pointer = (Pointer) EventGenerator.get(Event.getGeneratorID(event));
			int pointerX = pointer.getX();
			int pointerY = pointer.getY();
			if (Pointer.isReleased(event)) {
				if (landScape) {
					if (pointerX < padding) {
						testManager.showPreviousTest();
						return true;
					} else if (pointerX > width + padding) {
						testManager.showNextTest();
						return true;
					}
				} else {
					if ((pointerY < padding)) {
						testManager.showPreviousTest();
						return true;
					} else if (pointerY > height - padding) {
						testManager.showNextTest();
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Draws the title at the top of display
	 * @param graphicsContext GraphicsContext for drawing
	 * @param title String to draw
	 */
	protected void drawTitle(GraphicsContext graphicsContext, String title) {
		graphicsContext.setColor(Colors.BLACK);
		graphicsContext.drawString(title, width / 2  + x, 10, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
	}

	@Override
	/** Warning : Need to define this method, Kernel Exception */
	public EventHandler getController() {
		return null;
	}
}