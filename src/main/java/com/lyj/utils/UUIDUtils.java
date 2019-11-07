package com.lyj.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();//36λ  �ĸ� -
		uuid = uuid.replace("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getUUID());
	}

}
