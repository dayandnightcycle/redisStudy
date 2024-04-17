import com.lan.JedisConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class JedisTest {
    private Jedis jedis;

    @Before
    public void j() {
        //1.建立连接
        //jedis = new Jedis("192.168.10.100", 6379);
        jedis = JedisConnectionFactory.getJedis();
        //2.设置密码
        this.jedis.auth("123456");
        //3.选择库
        this.jedis.select(0);

    }

    @Test
    public void testString() {
        //存数据
        String set = jedis.set("name", "兰");
        System.out.println("set" + set);
        //取数据
        String name = jedis.get("name");
        System.out.println("name" + name);
    }

    @Test
    public void testHash(){
        HashMap<String, String> objectObjectHashMap = new HashMap<String, String>();
        objectObjectHashMap.put("name","lan");
        objectObjectHashMap.put("age","26");

        Long hset = jedis.hset("hm:user:3", objectObjectHashMap);
        System.out.println("hset = " + hset);
    }

    @After
    public void a() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
