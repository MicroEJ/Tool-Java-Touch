/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.is2t.pointer.test;

import com.is2t.pointer.framework.AbstractTest;
import com.is2t.pointer.framework.Environment;
import com.is2t.pointer.framework.Test;

import ej.microui.Colors;
import ej.microui.MicroUI;
import ej.microui.io.Display;
import ej.microui.io.Pointer;

/**
 * Launch all tests.
 */
public class TestsPointer extends AbstractTest {

	private static final int PADDING = 30;

	private final Test[] tests;
	private int currentTest;
	private final InnerEnvironment innerEnvironment;

	private int pressedX;

	private int pressedY;

	private boolean dragged;

	public static void main(String[] args) {
		MicroUI.errorLog(true);
		Display display = Display.getDefaultDisplay();
		Environment environment = new Environment(display, 0, 0, display.getWidth(), display.getHeight());
		TestsPointer test = new TestsPointer(environment);
		environment.pointer.setListener(test);
		test.start();
	}

	/**
	 * Initializes environment.
	 */
	public TestsPointer(Environment environment) {
		super(environment,"Global test");

		this.innerEnvironment = new InnerEnvironment(environment);
		this.tests = new Test[] { new TestThreshold(this.innerEnvironment), new TestFiltering(this.innerEnvironment),
				new TestCalibration(this.innerEnvironment), new TestDrag(this.innerEnvironment) };
	}

	@Override
	public void start() {
		super.start();

		showCurrentTest();
	}

	@Override
	public void performAction(int value) {
		int pointerX = this.environment.pointer.getX();
		int pointerY = this.environment.pointer.getY();
		int action = Pointer.getAction(value);
		switch (action) {
		case Pointer.PRESSED:
			if (pointerX < PADDING || pointerX > this.environment.width - PADDING) {
				this.pressedX = pointerX;
				this.pressedY = pointerY;
				this.dragged = false;
			}
			break;
		case Pointer.DRAGGED:
			this.dragged |= (this.pressedX != pointerX) || (this.pressedY != pointerY);
			break;
		case Pointer.RELEASED:
			if (!this.dragged) {
				if (pointerX < PADDING) {
					previousTest();
					return;
				} else if (pointerX > this.environment.width - PADDING) {
					nextTest();
					return;
				}
			}
			break;
		}
		this.tests[this.currentTest].performAction(value);
	}

	private void previousTest() {
		hideCurrentTest();
		if (this.currentTest == 0) {
			this.currentTest = this.tests.length - 1;
		} else {
			this.currentTest--;
		}
		showCurrentTest();
	}

	private void nextTest() {
		hideCurrentTest();
		if (this.currentTest == this.tests.length - 1) {
			this.currentTest = 0;
		} else {
			this.currentTest++;
		}
		showCurrentTest();
	}

	private void hideCurrentTest() {
		this.tests[this.currentTest].stop();
	}

	private void showCurrentTest() {
		this.tests[this.currentTest].start();
	}

	private class InnerEnvironment extends Environment {

		private final Environment parent;

		public InnerEnvironment(Environment parent) {
			super(parent.display, parent.x + PADDING, parent.y, parent.width - 2 * PADDING, parent.height, parent.g,
					parent.pointer);
			this.parent = parent;
		}

		@Override
		public void fillScreen(int color) {
			super.fillScreen(color);

			int centerY = this.parent.height / 2;
			this.g.setColor(Colors.GRAY);
			this.g.fillRect(0, 0, PADDING, this.parent.height);
			this.g.fillRect(this.parent.width - PADDING, 0, PADDING, this.parent.height);

			this.g.setColor(Colors.BLACK);
			this.g.drawLine(0, centerY, PADDING - 1, centerY - PADDING);
			this.g.drawLine(0, centerY, PADDING - 1, centerY + PADDING);
			this.g.drawLine(this.parent.width, centerY, this.parent.width - PADDING, centerY - PADDING);
			this.g.drawLine(this.parent.width, centerY, this.parent.width - PADDING, centerY + PADDING);
			this.g.flush();
		}

	}

}
