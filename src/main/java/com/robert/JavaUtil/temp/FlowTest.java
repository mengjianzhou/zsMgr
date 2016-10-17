package com.robert.JavaUtil.temp;

import java.util.Scanner;

public class FlowTest {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入a的值");
		int a = scanner.nextInt();
		System.out.println("请输入b的值");
		int b = scanner.nextInt();
		if(a==0&&b==0){
			System.out.println("a b 都是 0");
		} else{
			System.out.println("a 或者 b  都不是 0");
		}
	}
}
