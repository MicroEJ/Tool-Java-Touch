/**
 * Java
 *
 * Copyright 2017-2018 IS2T. All rights reserved.
 *
 * Use of this source code is subject to license terms.
 */
package com.microej.test.model;

import java.util.ArrayList;
import java.util.List;

import ej.microui.display.Colors;

public class ColorModel {

	/**
	 * Defaut background color.
	 */
	private int rgbaColor;

	/**
	 * List of listeners.
	 */
	private final List<ColorModelListener> modelListeners;

	/**
	 * Constructor
	 */
	public ColorModel(){
		modelListeners 	= new ArrayList<>();
		rgbaColor 		= Colors.WHITE;
	}

	/**
	 * Sets the default color.
	 * @param color new background color.
	 */
	public void setColor(int color){
		this.rgbaColor = color;
		fireUpdated();
	}

	/**
	 * Returns the default color.
	 * @return the default color.
	 */
	public int getColor(){
		return this.rgbaColor;
	}

	/**
	 * Adds a listener to the list that's notified each time a change
	 * to the data model occurs.
	 * @param colorModelListener the ColorModelListener
	 */
	public void addColorModelListener(ColorModelListener colorModelListener){
		this.modelListeners.add(colorModelListener);
	}

	/**
	 *  Notifies all listeners that the background color table's may have changed.
	 */
	protected void fireUpdated(){
		for(ColorModelListener  modelListener : modelListeners){
			modelListener.updated();
		}
	}
}
