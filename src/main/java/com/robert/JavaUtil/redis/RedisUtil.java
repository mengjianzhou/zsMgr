package com.robert.JavaUtil.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private static final String REDIS_HOST = "127.0.0.1";
	private static final int REDIS_IP = 6379;
	private static JedisPool jedisPool;
	
	public RedisUtil(){
		JedisPoolConfig config = new JedisPoolConfig(); 
		config.setMaxTotal(20); 
		config.setMaxIdle(5); 
		config.setMaxWaitMillis(5000);
		jedisPool = new JedisPool(config, REDIS_HOST, REDIS_IP);
	}
	
	/**
	 * 创建redis客户端
	 */
	public Jedis getRedisClient(){
		return jedisPool.getResource();
	}
	
	public static boolean acquireLock(Jedis jedis, String key, long expireTime){
		boolean locked = false;
		if(jedis.setnx(key, String.valueOf(expireTime))==1){
			return true;
		}
		while(true){
			String expiredTimeStr = jedis.get(key);
			if(expiredTimeStr!=null&&System.currentTimeMillis()>Long.parseLong(expiredTimeStr)){
				String oldExpiredTime = jedis.getSet(key, String.valueOf(expireTime));
				if(oldExpiredTime!=null&&oldExpiredTime.equals(expiredTimeStr)){
					locked =  true;
					break;
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return locked;
	}
	
}











