import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("unused")
public class Program {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        String ok = jedis.set("name", "lujun");
        System.out.println("set: "+ok);
        String name = jedis.get("name");
        System.out.println("get: "+name);
        Long i = jedis.del("name");
        System.out.println("del: "+i);
        name = jedis.get("name");
        System.out.println("get: "+name);
    }
}
