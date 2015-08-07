package com.squareup.timessquare;

import android.util.Log;

/** Log utility class to handle the log tag and DEBUG-only logging. */
final class Logr {
	static boolean debug = false;
	public static void d(String message) {
		if (debug) {
			Log.d("TimesSquare", message);
		}
	}

	public static void d(String message, Object... args) {
		if (debug) {
			d(String.format(message, args));
		}
	}
}
