/*
 * Java
 *
 * Copyright 2017 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.test;

import ej.wadapps.app.Activity;

public class ActivityTestPointer implements Activity {

	private static final String ID = "ActivityTestPointer";

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onRestart() {
	}

	@Override
	public void onStart() {
		// Entry Point
		Main.main(null);
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onDestroy() {
	}
}
