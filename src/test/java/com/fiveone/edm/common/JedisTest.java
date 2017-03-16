package com.fiveone.edm.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fiveone.edm.common.utils.RedisUtil;
import redis.clients.jedis.Jedis;

public class JedisTest {

	private Jedis jedis; 
    
    @Before
    public void setup() {
        //连接redis服务器
        jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
        jedis.auth("admin");  
    }

    /**
     * 测试RedisUtil
     */
    @Test
    public void testRedisUtil() {  
        RedisUtil.getJedis().set("lhw", "baobao");  
        System.out.println(RedisUtil.getJedis().get("lhw"));  
    }

    /**
     * 测试redis存储字符串
     */
    @Test
    public void testString() {
        //添加数据 
        jedis.set("name","baobao");//向key-->name中放入了value-->baobao 
        System.out.println(jedis.get("name"));//执行结果：baobao  
        //拼接
        jedis.append("name", " is my lover"); 
        System.out.println(jedis.get("name")); 
        //删除某个键
        jedis.del("name");  
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liu","age","23","qq","595971102");
        //进行加1操作
        jedis.incr("age"); 
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }


    /** 
     * 测试jedis操作List 
     */  
    @Test  
    public void testList(){  
        //开始前，先移除所有的内容  
        jedis.del("java framework");  
        System.out.println(jedis.lrange("java framework",0,-1));  
        //先向key java framework中存放三条数据  
        jedis.lpush("java framework","spring");  
        jedis.lpush("java framework","spirng mvc");  
        jedis.lpush("java framework","mybatis");  
        //jedis.llen(String key)获取长度 
        System.out.println(jedis.llen("java framework"));
        //jedis.lrange返回存储在 key的列表里指定范围内的元素     -1表示取得所有    
        //第一个是key，第二个是起始位置(list的第一个元素下标是0)，第三个是结束位置
        System.out.println(jedis.lrange("java framework",0,-1));  
        
        jedis.del("java framework");
        jedis.rpush("java framework","spring");  
        jedis.rpush("java framework","spirng jdbc");  
        jedis.rpush("java framework","spring boot"); 
        System.out.println(jedis.lrange("java framework",0,-1));
    }  
        
    /** 
     * 测试jedis操作Set 
     */  
    @Test  
    public void testSet(){  
        //添加  
        jedis.sadd("user","what");  
        jedis.sadd("user","who");  
        jedis.sadd("user","why");  
        jedis.sadd("user","when");
        jedis.sadd("user","how");  
        //遍历集合 
        System.out.println(jedis.smembers("user"));
        //Redis srem命令用于移除集合中的一个或多个成员元素，不存在的成员元素会被忽略  
        jedis.srem("user","who"); 
        //遍历集合 
        System.out.println(jedis.smembers("user"));
        //是否包含      who 是否是user集合的元素 
        System.out.println(jedis.sismember("user", "who")); 
        //如果命令执行时，只提供了 key参数，那么返回集合中的一个随机元素
        System.out.println(jedis.srandmember("user"));  
        //返回集合的元素个数  
        System.out.println(jedis.scard("user"));
    } 

    /**
     * 测试redis操作Map
     */
    @Test
    public void testMap() {
        //添加数据 
        Map<String, String> map = new HashMap<String, String>();
        map.put("English", "英语");
        map.put("Chinese", "汉语");
        map.put("Japanese", "日语");
        jedis.hmset("language",map);
        //取出user中的name，执行结果:[element1,element2,...]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("language", "English", "Chinese", "Japanese");
        System.out.println(rsmap);  
  
        //删除map中的某个键值  
        jedis.hdel("language","Japanese");
        //因为删除了，所以返回的是null  
        System.out.println(jedis.hmget("language", "Japanese"));  
        //返回key为user的键中存放的值的个数2 
        System.out.println(jedis.hlen("language")); 
        //是否存在key为user的记录 返回true 
        System.out.println(jedis.exists("language")); 
        //返回map对象中的所有key
        System.out.println(jedis.hkeys("language"));
        //返回map对象中的所有value 
        System.out.println(jedis.hvals("language"));
  
        Iterator<String> iter=jedis.hkeys("language").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("language",key));  
        }  
    }
    
    /**
     * 测试排序
     * @throws InterruptedException
     */
     @Test  
    public void testSort() throws InterruptedException {  
        //jedis 从小到大排序  
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（从表现来看的）
    	//先清除数据，再加入数据进行测试  
        jedis.del("number");
        //jedis.rpush 右边入队
        jedis.rpush("number","2");
        //jedis.lpush 左边入队
        jedis.lpush("number","7");  
        jedis.lpush("number","6");
        jedis.lpush("number","8"); 
        jedis.lpush("number","9");  
        //返回列表范围：从0开始，到最后一个(-1) [包含] 
        System.out.println(jedis.lrange("number",0,-1));//[9, 8, 6, 7, 2]
        //jedis.sort 从小到大排序  
        System.out.println(jedis.sort("number")); //[2, 6, 7, 8, 9] 输入排序后结果  
        System.out.println(jedis.lrange("number",0,-1)); //[9, 8, 6, 7, 2]  
    }  

}
