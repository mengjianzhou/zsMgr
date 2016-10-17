package com.robert.JavaUtil.flow;

public class FinallyTest {
	
	public static void main(String[] args) {
		
		try {
			System.out.println("hello");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("abc");
		}
		
		System.out.println("test1");
		
	}
	
}
