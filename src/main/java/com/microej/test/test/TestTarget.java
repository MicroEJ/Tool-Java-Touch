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
import ej.microui.event.Event;
import ej.microui.event.EventGenerator;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

public class TestTarget extends AbstractTest {

	private static final String NAME = "Target";

	int color;
	int counter;
	private int touchedX = -1;
	private int touchedY = -1;
	private int targetX;
	private int targetY;
	private int newTargetX;
	private int newTargetY;

	private int deltaX;
	private int deltaY;

	public TestTarget(TestManager testManager) {
		super(NAME, testManager);

		this.color = Colors.RED;
		this.counter = 0;
		this.newTargetX = this.width / 2;
		this.newTargetY = this.height / 2;
	}

	@Override
	public boolean handleEvent(int event) {

		if(super.handleEvent(event)) {
			return true;
		}

		if (Event.getType(event) == Event.POINTER) {
			Pointer p = (Pointer) EventGenerator.get(Event.getGeneratorID(event));
			this.touchedX = p.getX();
			this.touchedY = p.getY();
			if (Pointer.isPressed(event)) {
				this.deltaX = this.touchedX - this.newTargetX;
				this.deltaY = this.touchedY - this.newTargetY;

				System.out.println("Pointer is pressed: x = " + this.touchedX + " y = " + this.touchedY);
				if ((this.targetX == this.touchedX) && (this.targetY == this.touchedY)) {
					System.out.println("Perfect hit !");
				} else {
					System.out.println("deltaX = " + (this.deltaX) + " deltaY = " + (this.deltaY));
				}
			} else if (Pointer.isReleased(event)) {
				System.out.println("Pointer is released: x = " + this.touchedX + " y = " + this.touchedY);
				this.targetX = this.newTargetX;
				this.targetY = this.newTargetY;
				this.newTargetX = (int) (Math.random() * this.width) + this.x;
				this.newTargetY = (int) (Math.random() * this.height) + this.y;
				System.out.println("New Target: x = " + this.newTargetX + " y = " + this.newTargetY);
				repaint();
			} else if (Pointer.isDragged(event)) {
				System.out.println("Pointer is dragged: x = " + this.touchedX + " y = " + this.touchedY);
			}

			return true;
		}
		return false;
	}

	@Override
	public void paint(GraphicsContext g) {
		this.clearScreen(g);
		super.paint(g);

		if (this.touchedX >= 0) {
			paintTarget(g, this.targetX, this.targetY, Colors.GRAY);
			paintTouched(g, this.touchedX, this.touchedY);
		}
		paintTarget(g, this.newTargetX, this.newTargetY, Colors.BLACK);
		paintDelta(g);
	}

	private void paintTarget(GraphicsContext g, int x, int y, int color) {
		g.setColor(Colors.WHITE);

		int radius = Math.min(this.height / 10, this.width / 10);
		g.fillCircle(x - radius, y - radius, radius * 2);
		g.setColor(color);
		g.drawCircle(x - radius, y - radius, radius * 2);
		radius -= 5;
		g.fillCircle(x - radius, y - radius, radius * 2);
		radius -= 5;
		g.setColor(Colors.WHITE);
		g.fillCircle(x - radius, y - radius, radius * 2);
		radius -= 5;
		g.setColor(color);
		g.fillCircle(x - radius, y - radius, radius * 2);
		radius -= 5;
		g.setColor(Colors.WHITE);
		g.fillCircle(x - radius, y - radius, radius * 2);
		radius -= 5;
		g.setColor(color);
		g.fillCircle(x - radius, y - radius, radius * 2);

		g.drawLine(x, y - radius, x, y + radius);
		g.drawLine(x - radius, y, x + radius, y);
	}

	private void paintTouched(GraphicsContext g, int x, int y) {
		g.setColor(Colors.RED);
		g.fillRect(x - 2, y - 20, 4, 40);
		g.fillRect(x - 20, y - 2, 40, 4);
	}

	private void paintDelta(GraphicsContext g) {
		g.setColor(Colors.BLACK);
		g.drawString("Delta X : " + this.deltaX + "px - Delta Y : " + this.deltaY + " px", this.x,
				this.y, GraphicsContext.TOP | GraphicsContext.LEFT);
	}

	@Override
	public EventHandler getController() {
		return this;
	}
}
