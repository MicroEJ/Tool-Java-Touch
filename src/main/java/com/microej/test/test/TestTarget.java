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
import ej.microui.event.Event;
import ej.microui.event.EventGenerator;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Tests the targeting with a Pointer.
 */
public class TestTarget extends AbstractTest {

	private static final int DIST_TO_CROSS_BAR_START = 20;

	private static final int CROSS_BAR_LENGTH = 40;

	private static final int CROSS_BAR_THICKNESS = 4;

	private static final int CIRCLE_RADIUS_DECREASE = 5;

	private static final String NAME = "Target";

	private static final String Y_ASSIGN = " y = ";

	private int touchedX;
	private int touchedY;
	private int previousTargetX;
	private int previousTargetY;
	private int currentTargetX;
	private int currentTargetY;

	private int deltaX;
	private int deltaY;

	/**
	 * Initializes environment.
	 *
	 * @param testManager the provided testManager
	 */
	public TestTarget(TestManager testManager) {
		super(NAME, testManager);
		this.currentTargetX = super.getInnerWidth() / 2;
		this.currentTargetY = super.getInnerHeight() / 2;
		this.touchedX = -1;
		this.touchedY = -1;
	}

	@Override
	public boolean handleEvent(int event) {

		if(super.handleEvent(event)) {
			return true;
		}

		if (Event.getType(event) == Pointer.EVENT_TYPE) {
			Pointer p = (Pointer) EventGenerator.get(Event.getGeneratorId(event));
			this.touchedX = p.getX();
			this.touchedY = p.getY();
			if (Buttons.isPressed(event)) {
				this.deltaX = this.touchedX - this.currentTargetX;
				this.deltaY = this.touchedY - this.currentTargetY;

				System.out.println("Pointer is pressed: x = " + this.touchedX + Y_ASSIGN + this.touchedY);
				if ((this.currentTargetX == this.touchedX) && (this.currentTargetY == this.touchedY)) {
					System.out.println("Perfect hit !");
				} else {
					System.out.println("deltaX = " + (this.deltaX) + " deltaY = " + (this.deltaY));
				}
			} else if (Buttons.isReleased(event)) {
				System.out.println("Pointer is released: x = " + this.touchedX + Y_ASSIGN + this.touchedY);
				this.previousTargetX = this.currentTargetX;
				this.previousTargetY = this.currentTargetY;
				this.currentTargetX = (int) (Math.random() * getInnerWidth()) + getX();
				this.currentTargetY = (int) (Math.random() * getInnerHeight()) + getY();
				System.out.println("New Target: x = " + this.currentTargetX + Y_ASSIGN + this.currentTargetY);
				requestRender();
			} else if (Pointer.isDragged(event)) {
				System.out.println("Pointer is dragged: x = " + this.touchedX + Y_ASSIGN + this.touchedY);
			}

			return true;
		}
		return false;
	}

	@Override
	public void render(GraphicsContext g) {
		this.clearScreen(g);
		super.render(g);
		drawTitle(g, getTitle());

		if (this.touchedX >= 0) {
			paintTarget(g, this.previousTargetX, this.previousTargetY, Colors.GRAY);
			paintTouched(g, this.touchedX, this.touchedY);
		}
		paintTarget(g, this.currentTargetX, this.currentTargetY, Colors.BLACK);
		paintDelta(g);
	}

	private void paintTarget(GraphicsContext g, int x, int y, int color) {
		g.setColor(Colors.WHITE);
		final int tenthOfScreenHeight = getInnerHeight() / 10;
		final int tenthOfScreenWidth = getInnerWidth() / 10;
		int radius = Math.min(tenthOfScreenHeight, tenthOfScreenWidth);
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);
		g.setColor(color);
		Painter.drawCircle(g, x - radius, y - radius, radius * 2);
		radius -= CIRCLE_RADIUS_DECREASE;
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);
		radius -= CIRCLE_RADIUS_DECREASE;
		g.setColor(Colors.WHITE);
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);
		radius -= CIRCLE_RADIUS_DECREASE;
		g.setColor(color);
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);
		radius -= CIRCLE_RADIUS_DECREASE;
		g.setColor(Colors.WHITE);
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);
		radius -= CIRCLE_RADIUS_DECREASE;
		g.setColor(color);
		Painter.fillCircle(g, x - radius, y - radius, radius * 2);

		Painter.drawLine(g, x, y - radius, x, y + radius);
		Painter.drawLine(g, x - radius, y, x + radius, y);
	}

	private void paintTouched(GraphicsContext g, int x, int y) {
		g.setColor(Colors.RED);
		Painter.fillRectangle(g, x - 2, y - DIST_TO_CROSS_BAR_START, CROSS_BAR_THICKNESS, CROSS_BAR_LENGTH);
		Painter.fillRectangle(g, x - DIST_TO_CROSS_BAR_START, y - 2, CROSS_BAR_LENGTH, CROSS_BAR_THICKNESS);
	}

	private void paintDelta(GraphicsContext g) {
		g.setColor(Colors.BLACK);
		Painter.drawString(g, "Delta X : " + this.deltaX + "px - Delta Y : " + this.deltaY + " px",
				Font.getDefaultFont(), getX(), getY());
	}
}
