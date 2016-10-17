package com.robert.JavaUtil.redis;

import java.util.Random;

public class RandomUtils {
	
	public static void main(String[] args) {
		Random random = new Random();
		System.out.println();
		int num=20;
		while(num!=(random.nextInt(20))){
			System.out.println("...");
		}
		System.out.println("20");
	}

}
