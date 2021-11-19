package com.framework.commonUtils;

import java.util.Random;

public class Utils {

	public static String generateRandomStrings(String refString, String chars, int length) {

		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));

		}
		return refString + "_" + sb.toString();
	}

}
