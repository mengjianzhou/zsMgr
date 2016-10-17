package com.robert;

import java.util.Random;

public class RandomHongBao {
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){			
			System.out.println(getRandomHB(1));
		}
	}
	
	public static int getRandomHB(int probability){
		if(calGaiLv(probability)==1){			
			return getBigHB();
		} else{			
			return getSmallHB();
		}
	}

	private static int getSmallHB() {
		Random random = new Random();
		int ranNum = 0;
		do{
			ranNum = random.nextInt(30);
		}while(ranNum<=10);
		return ranNum;
	}

	private static int getBigHB() {
		Random random = new Random();
		int ranNum = 0;
		do{
			ranNum = random.nextInt(100);
		}while(ranNum<=30);
		return ranNum;
	}
	
	/**
	 * num 表示num%的一个概率
	 * @param num
	 */
	public static int calGaiLv(int num){
		int[] arr = new int[num];
		Random random = new Random();
		int ranNum;
		for(int i=0;i<num;i++){			
			do{
				ranNum = random.nextInt(100);
			}while(ranNum<=0);
			arr[i] = ranNum;
		}
		
		Random newRandom = new Random();
		int luckBall;
		do{
			luckBall = random.nextInt(100);
		}while(luckBall<=0);
		
		for(int ball:arr){
			if(ball==luckBall){
				System.out.println("您中奖了！");
				return 1;
			}
		}
		System.out.println("谢谢惠顾");
		return 0;
	}
}
