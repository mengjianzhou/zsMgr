package com.robert.www.JavaUtil.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	public static void main(String[] args) {
		
		RedisUtil redisUtil = new RedisUtil();
		Jedis jedis = redisUtil.getRedisClient();
		//append
		jedis.append("name", "11");
	}
}
