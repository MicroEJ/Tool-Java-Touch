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
import com.microej.test.model.ColorModel;
import com.microej.test.model.ColorModelListener;

import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

/**
 * Tests the dragging events distribution.
 */
public class TestFiltering extends AbstractTest {

	private boolean blink = false;

	private final ColorModel model;
	private final Timer timer;

	/**
	 * Initializes environment.
	 */
	public TestFiltering(TestManager testManager) {
		super("Filtering", testManager);
		this.timer = new Timer();
		this.model = new ColorModel();
		this.model.addColorModelListener(new ColorModelListener() {
			@Override
			public void updated() {
				repaint();
			}
		});
	}

	@Override
	public boolean handleEvent(int event) {

		if (super.handleEvent(event)){
			return true;
		}

		int action = Pointer.getAction(event);
		switch (action) {
		case Pointer.PRESSED:
			this.blink = false;
			this.model.setColor(Colors.WHITE);
			break;
		case Pointer.DRAGGED:
			this.blink = true;
			this.model.setColor(Colors.RED);
			return true;
		case Pointer.RELEASED:
			this.blink = false;
			this.model.setColor(Colors.WHITE);
			break;
		}
		return false;
	}

	@Override
	public void paint(GraphicsContext g) {
		clearScreen(g, this.model.getColor());
		super.paint(g);
		drawTitle(g, getTitle());
		if (this.blink) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					model.setColor(Colors.WHITE);
				}
			}, 10);
		}
	}

	@Override
	public EventHandler getController() {
		return this;
	}
}
