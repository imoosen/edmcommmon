package com.fiveone.edm.common.utils;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池工具类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月9日 下午5:22:31
 * @version: 1.0
 * @since: JDK1.7
 */
public class RedisUtil {

	private static Logger log = Logger.getLogger(RedisUtil.class); 
	
	//jedis连接池
	private static JedisPool jedisPool = null;
	
	//Redis服务器IP
	private static String IP = "127.0.0.1";

	//Redis的端口号
	private static int PORT = 6379;

	//访问密码
	private static String AUTH = "admin";

	//最大连接数，默认8个；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 200;

	//最大空闲连接数，默认8个。
	private static int MAX_IDLE = 30;

	//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted)，默认值为-1，表示永不超时。
	//如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 1800000;
	
	//逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
	private static int MIN_WAIT = 1800000;
	
	//对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,
	//不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
	private static int SORT_MIN_WAIT = 1800000;

	private static int TIMEOUT = 10000;

	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setMinEvictableIdleTimeMillis(MIN_WAIT);
            config.setSoftMinEvictableIdleTimeMillis(SORT_MIN_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            /*
			//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            config.setBlockWhenExhausted(true);
            //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy"); 
            //是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            //是否启用后进先出, 默认true
            config.setLifo(true);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(false);
            //在空闲时检查有效性, 默认false
            config.setTestWhileIdle(false);
            //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            config.setTimeBetweenEvictionRunsMillis(-1);
            //最小空闲连接数, 默认0
            config.setMinIdle(0);
            //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            config.setNumTestsPerEvictionRun(3);
            */
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT,AUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis jedis = jedisPool.getResource();
				return jedis;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("JedisPool连接出现异常\n"+ e.toString());  
			return null;
		}
	}
	
	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public static void close(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();;
		}
	}
}
