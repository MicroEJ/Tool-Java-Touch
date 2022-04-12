/**
 * Java
 *
 * Copyright 2017-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.test.test;

import com.microej.test.framework.AbstractTest;
import com.microej.test.framework.TestManager;
import com.microej.test.model.ColorModel;
import com.microej.test.model.ColorModelListener;

import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;

/**
 * Tests the dragging events distribution.
 */
public class TestFiltering extends AbstractTest {

	private static final int DELAY_IN_MILLIS = 10;

	private boolean blink = false;

	private final ColorModel model;
	private final Timer timer;

	/**
	 * Initializes environment.
	 *
	 * @param testManager the provided testManager
	 */
	public TestFiltering(TestManager testManager) {
		super("Filtering", testManager);
		this.timer = new Timer();
		this.model = new ColorModel();
		this.model.addColorModelListener(new ColorModelListener() {
			@Override
			public void updated() {
				requestRender();
			}
		});
	}

	@Override
	public boolean handleEvent(int event) {

		if (super.handleEvent(event)){
			return true;
		}

		int action = Buttons.getAction(event);
		if ((action == Buttons.PRESSED) || (action == Buttons.RELEASED)) {
			this.blink = false;
			this.model.setColor(Colors.WHITE);
			return false;
		} else if (action == Pointer.DRAGGED) {
			this.blink = true;
			this.model.setColor(Colors.RED);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void render(GraphicsContext g) {
		clearScreen(g, this.model.getColor());
		super.render(g);
		drawTitle(g, getTitle());
		if (this.blink) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					model.setColor(Colors.WHITE);
				}
			}, DELAY_IN_MILLIS);
		}
	}
}
