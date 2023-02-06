import com.lan.JedisConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    private Jedis jedis;

    @Before
    public void j() {
        //1.建立连接
        //jedis = new Jedis("192.168.10.100", 6379);
        jedis = JedisConnectionFactory.getJedis();
        //2.设置密码
        this.jedis.auth("123321");
        //3.选择库
        this.jedis.select(0);

    }

    @Test
    public void testString() {
        //存数据
        String set = jedis.set("name", "pop");
        System.out.println("set" + set);
        //取数据
        String name = jedis.get("name");
        System.out.println("name" + name);

    }


    @After
    public void a() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
