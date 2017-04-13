package com.redis;

import redis.clients.jedis.Jedis;

/**
 * redis客户端
 * @author zengjintao
 *
 */
public class RedisClient {

	public static void main(String[] args) {
		Jedis redis=new Jedis("localhost", 6379);
//		/redis.auth("123456");
		//redis.connect();
		redis.set("name", "sddsd");
		System.out.println("连接成功");
		System.out.println(redis.get("name"));
	}
		
}
