package com.robert;

import redis.clients.jedis.Jedis;

public class RedisClient {
	
    public static void main(String[] args) {
    	System.out.println(getHongBao());
    	System.out.println();
	}
    
    public static void doAppend(StringBuilder script,String expression){
    	script.append(expression+"\n");
    }
    
    public static int getHongBao(){
    	Jedis jedis = new Jedis("localhost", 6379);
    	jedis.set("name","robert");
    	StringBuilder script = new StringBuilder();
    	doAppend(script,"local result = 0");
    	doAppend(script,"local afterDescNum = redis.call('decr', KEYS[1])");
    	doAppend(script,"if afterDescNum>=0 then");
    	doAppend(script,"result=1");
    	doAppend(script,"else ");
    	doAppend(script,"result=0");
    	doAppend(script,"redis.call('set', KEYS[1], 0)");
    	doAppend(script,"end");
    	script.append("return result");
    	Object result = jedis.eval(script.toString(),1,"hongbaoNum");
		int flag = Integer.parseInt(String.valueOf(result));
		System.out.println(Thread.currentThread().getName()+" : "+flag);
		return flag;
    }
}
