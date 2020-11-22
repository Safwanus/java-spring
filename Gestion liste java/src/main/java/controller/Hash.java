
package main.java.controller;

public class Hash {

	public static int sha1(String stringToEncrypt) {
		int strlen = stringToEncrypt.length();
		int hash = 7;
		for (int i = 0; i < strlen; i++) {
			hash = hash * 31 + stringToEncrypt.charAt(i);
		}

		return hash;
	}

}
